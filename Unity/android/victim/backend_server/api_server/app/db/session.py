from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from ..settings import settings


engine = create_engine(settings.DATABASE_URI, pool_pre_ping=True)
Session = sessionmaker(autocommit=False, autoflush=False, bind=engine)
