from typing import Optional

from fastapi import FastAPI, status, HTTPException
import pymysql

app = FastAPI()


def connect_db():
    _db = None
    try:
        _db = pymysql.connect(
            host="mysql_db",
            db="testdb",
            user="test_user",
            password="password"
        )
    except Exception as e:
        print(e)
    return _db


g_db = connect_db()


def get_db():
    global g_db
    if g_db is None:
        g_db = connect_db()
    if g_db is None:
        raise Exception('Connection to db failed.')
    return g_db


@app.get("/")
def root():
    return {"Hello": "World"}


@app.get("/search_vul_union")
def search_vul_union(key: str):
    print(f'key : {key}')

    sql = "SELECT k,v FROM kv WHERE k LIKE '%" + key + "%';"

    db = get_db()
    try:
        cur = db.cursor()
        cur.execute(sql)
    except Exception as e:
        cur.close()
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail="db error"
        )

    data = cur.fetchall()
    return {'data': data}


@app.get("/search_vul_error")
def search_vul_error(key: str):
    print(f'key : {key}')

    sql = "select k from kv where k = '" + key + "';"
    print(sql)

    db = get_db()
    try:
        cur = db.cursor()
        cur.execute(sql)
    except Exception as e:
        cur.close()
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"db error : {e}"
        )

    data = cur.fetchall()
    if len(data) == 0:
        return {'status': 'not used'}
    return {'status': 'used'}


@app.get("/search_vul_boolean")
def search_vul_boolean(key: str):
    print(f'key : {key}')

    sql = "select k from kv where k = '" + key + "';"
    print(sql)

    db = get_db()
    try:
        cur = db.cursor()
        cur.execute(sql)
    except Exception as e:
        cur.close()
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"db error"
        )

    data = cur.fetchall()
    if len(data) == 0:
        return {'status': 'not used'}
    return {'status': 'used'}
