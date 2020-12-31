import random

from flask import Flask


app = Flask(__name__)


@app.route('/janken')
def janken():
    jkn_results = {
        0: 'guu',
        1: 'tyoki',
        2: 'paa'
    }
    return jkn_results[random.randint(0, 2)]


if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=5000)
