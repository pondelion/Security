from sqlalchemy import Boolean, Column, Integer, String
from sqlalchemy.orm import relationship

from ..db.base import Base


class User(Base):
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    hashed_password = Column(String, nullable=False)
    is_active = Column(Boolean(), default=True)
    is_superuser = Column(Boolean(), default=False)
