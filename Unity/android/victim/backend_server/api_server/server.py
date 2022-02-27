from fastapi import FastAPI
from app.api import api_router
from app.settings import settings

app = FastAPI(title='unity_backend')


app.include_router(api_router, prefix=settings.API_V1_STR)