import os
from typing import Optional
import json

from fastapi import Depends, FastAPI, HTTPException, status
from fastapi.security import OAuth2PasswordBearer
from fastapi.middleware.cors import CORSMiddleware
import requests
from jose import jwt
from pydantic import BaseModel, EmailStr, AnyHttpUrl


AUTH0_DOMAIN = os.environ['AUTH0_DOMAIN']
AUDIENCE = os.environ['AUDIENCE']
CLIENT_ID = os.environ['CLIENT_ID']
CLIENT_SECRET = os.environ['CLIENT_SECRET']
CONNECTION_DB_NAME = 'Username-Password-Authentication'
ALGORITHMS = ["RS256"]
app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
oauth2_scheme = OAuth2PasswordBearer(tokenUrl='token')


class UserCreate(BaseModel):
    email: EmailStr
    password: str
    username: Optional[str]
    given_name: Optional[str] = None
    family_name: Optional[str] = None
    name: Optional[str] = None
    nickname: Optional[str] = None
    # picture: Optional[AnyHttpUrl] = None
    picture: Optional[str] = None


def get_management_api_token() -> str:
    url = f'https://{AUTH0_DOMAIN}/oauth/token'
    headers = {'Content-Type': 'application/x-www-form-urlencoded'}
    data = {
        'grant_type': 'client_credentials',
        'client_id': CLIENT_ID,
        'client_secret': CLIENT_SECRET,
        'audience': AUDIENCE,
    }
    r = requests.post(url=url, data=data, headers=headers)
    return r.json()['access_token']


MGMT_TOKEN = get_management_api_token()
print(f'MGMT_TOKEN : {MGMT_TOKEN}')


def requires_auth(token: str = Depends(oauth2_scheme)):
    print(f'token :{token}')
    jsonurl = f'https://{AUTH0_DOMAIN}/.well-known/jwks.json'
    jwks = requests.get(jsonurl).json()
    # print(f'jwks : {jwks}')
    try:
        unverified_header = jwt.get_unverified_header(token)
    except jwt.JWTError as e:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail='Invalid header. Use an RS256 signed JWT Access Token'
        )
    if unverified_header['alg'] == 'HS256':
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail='Invalid header. Use an RS256 signed JWT Access Token'
        )
    rsa_key = {}
    print(unverified_header)
    for key in jwks['keys']:
        if key['kid'] == unverified_header['kid']:
            rsa_key = {
                'kty': key['kty'],
                'kid': key['kid'],
                'use': key['use'],
                'n': key['n'],
                'e': key['e'],
            }
    if rsa_key:
        try:
            payload = jwt.decode(
                token,
                rsa_key,
                algorithms=ALGORITHMS,
                audience=AUDIENCE,
                issuer=f'https://{AUTH0_DOMAIN}/'
            )
        except jwt.ExpiredSignatureError as e:
            raise HTTPException(
                status_code=status.HTTP_401_UNAUTHORIZED,
                detail='Token is expired'
            )
        except jwt.JWTClaimsError as e:
            raise HTTPException(
                status_code=status.HTTP_401_UNAUTHORIZED,
                detail='Incorrect claims. Please check the audience and issur'
            )
        except Exception as e:
            raise HTTPException(
                status_code=status.HTTP_401_UNAUTHORIZED,
                detail='Unable to parse authentication token'
            )
    return payload


@app.get('/private')
async def private(payload: str = Depends(requires_auth)):
    return {'message': 'This is secret data', 'your_data': payload}


@app.get('/token')
async def token(username: str, password: str):
    url = f'https://{AUTH0_DOMAIN}/oauth/token'
    headers = {'Content-Type': 'application/x-www-form-urlencoded'}
    data = {
        'grant_type': 'password',
        'username': username,
        'password': password,
        'client_id': CLIENT_ID,
        'client_secret': CLIENT_SECRET,
        'audience': AUDIENCE,
    }
    r = requests.post(url=url, data=data, headers=headers)
    return r.json()


@app.get('/role')
async def role(payload: str = Depends(requires_auth)):
    print(f'payload : {payload}')
    url = f'https://{AUTH0_DOMAIN}/api/v2/users/{payload["sub"]}/roles'
    headers = {
        'Authorization': f'Bearer {MGMT_TOKEN}'
    }
    r = requests.get(url=url, headers=headers)
    return r.json()


@app.post('/user')
async def signup(user: UserCreate):
    url = f'https://{AUTH0_DOMAIN}/dbconnections/signup'
    headers = {'Content-Type': 'application/json'}
    data = user.dict(exclude_unset=True)
    data['client_id'] = CLIENT_ID
    data['connection'] = CONNECTION_DB_NAME
    print(data)
    r = requests.post(url=url, data=json.dumps(data), headers=headers)
    return r.json()
