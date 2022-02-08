import os
from io import BytesIO

from flask import (
    Flask,
    jsonify,
    request,
)
import pycurl
import redis


app = Flask(__name__)
rds = redis.Redis(host='127.0.0.1', port=6379, db=0)

# export FLAG='flaggg'
rds.set('FLAG', os.environ['FLAG'])


@app.route('/')
def index():
    url = request.args['url']
    print(url)

    buf = BytesIO()
    c = pycurl.Curl()
    c.setopt(c.URL, url)
    c.setopt(c.WRITEDATA, buf)
    c.perform()
    c.close()

    body = buf.getvalue().decode('UTF-8')
    return body, 200


app.run(host='0.0.0.0', port=5000, debug=False)
