from typing import Generator, Dict, Any, Union
from datetime import datetime, timedelta
import secrets

from fastapi import FastAPI, APIRouter, Depends, HTTPException, status
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm
from pydantic import BaseModel
from jose import jwt
from passlib.context import CryptContext


app = FastAPI()
router = APIRouter()
oauth2 = OAuth2PasswordBearer(
    tokenUrl='/login/access-token'
)
SECRET_KEY = secrets.token_urlsafe(32)
ALGORITHM = 'HS256'
crypt_context = CryptContext(schemes=['bcrypt'], deprecated='auto')


#### Models ####

class UserCreate(BaseModel):
    username: str
    password: str


#### utility funcs #####

def get_password_hash(password: str) -> str:
    return crypt_context.hash(password)


def verify_password(plain_password: str, hashed_password: str) -> bool:
    return crypt_context.verify(plain_password, hashed_password)


def authenticate(db: Dict, name: str, password: str) -> Union[Dict, None]:
    if name not in db:
        print(f'user with name {name} does not exists in db')
        return None
    user = db[name]
    if not verify_password(password, user['hashed_password']):
        print('password verify failed')
        return None
    return user


def create_access_token(
    subject: Union[str, Any], expires_delta: timedelta = timedelta(minutes=60*6),
) -> str:
    expire = datetime.utcnow() + expires_delta
    token = {'exp': expire, 'sub': str(subject)}
    encoded_token = jwt.encode(token, SECRET_KEY, algorithm=ALGORITHM)
    return encoded_token


g_db_user = {
    'super_user': {
        'id': 'super_user',
        'name': 'super_user',
        'hashed_password': get_password_hash('possword'),
    }
}


#### Dependencies ####

def get_db() -> Generator:
    global g_db_user
    yield g_db_user


def get_current_user(
    db = Depends(get_db),
    token: str = Depends(oauth2),
) -> Dict:
    try:
        payload = jwt.decode(
            token, SECRET_KEY, algorithms=[ALGORITHM]
        )
    except jwt.JWTError:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail='Could not validate credentials'
        )
    if payload['sub'] not in db:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="User not found"
        )
    user = db[payload['sub']]
    return user


#### Endpoints #####

@router.post('/login/access-token')
def login_access_token(
    db: Dict = Depends(get_db),
    form_data: OAuth2PasswordRequestForm = Depends()
) -> Any:
    user = authenticate(db, form_data.username, form_data.password)
    if not user:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail='Inccorect username or password',
        )
    access_token_expires = timedelta(minutes=60*6)
    return {
        'access_token': create_access_token(user['id'], expires_delta=access_token_expires),
        'token_type': 'bearer',
    }


@router.post('/user')
def register_user(
    user: UserCreate,
    db: Dict = Depends(get_db),
):
    if user.username in g_db_user:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail=f'User {user.username} already exists',
        )
    db[user.username] = {
        'id': user.username,
        'name': user.username,
        'hashed_password': get_password_hash(user.password),
    }
    access_token_expires = timedelta(minutes=60*6)
    return {
        'access_token': create_access_token(db[user.username]['id'], expires_delta=access_token_expires),
        'token_type': 'bearer',
    }


@router.get('/secret_info')
def secret_info(
    db: Dict = Depends(get_db),
    current_user: Dict = Depends(get_current_user),
) -> Any:
    return {
        'message': 'this is login-reuired secret data',
        'user': current_user
    }


@router.get('/dump_users')
def dump_users(
    db: Dict = Depends(get_db),
) -> Any:
    print(db)
    return 'OK'


app.include_router(router)
