FROM python:3.8

ADD ./requirements.txt ./
ADD ./app.py ./
RUN apt-get update -y&&\
    apt-get install -y default-mysql-client &&\
	useradd -s /usr/sbin/nologin ctf &&\
	pip install -r ./requirements.txt

USER ctf

CMD ["uvicorn", "app:app", "--reload", "--host", "0.0.0.0"]
EXPOSE 8000
