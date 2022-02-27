
from .base import BaseDBRepository
from ...models.user import User
from ...schemas.user import UserCreate, UserUpdate


class DBUserRepository(BaseDBRepository[User, UserCreate, UserUpdate]):
    