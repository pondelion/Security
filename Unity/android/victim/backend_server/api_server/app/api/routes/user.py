from typing import Any, List

from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session

from ..dependencies import dependencies
from ... import schemas, models


router = APIRouter(prefix='/user')


@router.get('/', response_model=List[schemas.User])
def read_users(
    db: Session = Depends(dependencies.get_db),
    skip: int = 0,
    limit: int = 100,
    current_user: models.User = Depends(dependencies.get_current_active_user),
) -> Any:
    return 'ok'
