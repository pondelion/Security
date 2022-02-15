
## Run server

```bash
$ uvicorn server:app --reload
```

## Register user

```bash
curl -X POST "http://localhost:8000/user" -H "Content-Type: application/json" -d '{"username": "testuser1", "password": "pass"}'
```

## Get access token by registered user account

- registered account

```bash
$ curl -X POST "http://localhost:8000/login/access-token" -H  "accept: application/json" -H  "Content-Type: application/x-www-form-urlencoded" -d "username=testuser1&password=pass"
{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NDQ5NDI4MjAsInN1YiI6InRlc3R1c2VyMSJ9.WGg-C2NxXoSBaDZsfOy0FhXzW0TbSjJgMCOWAeNbWF8","token_type":"bearer"}
```

- not registered account

```bash
$ curl -X POST "http://localhost:8000/login/access-token" -H  "accept: application/json" -H  "Content-Type: application/x-www-form-urlencoded" -d "username=non_exist_user&password=pass"
{"detail":"Inccorect username or password"}
```

## Get secret info

- Withoud access token

```bash
$ curl "http://localhost:8000/secret_info"
{"detail":"Not authenticated"}
```

- With wrong access token

```bash
$ curl "http://localhost:8000/secret_info" -H "accept: application/json" -H "Authorization: Bearer AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
{"detail":"Could not validate credentials"}
```

- With correct access token

```bash
$ curl "http://localhost:8000/secret_info" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NDQ5NDI4MjAsInN1YiI6InRlc3R1c2VyMSJ9.WGg-C2NxXoSBaDZsfOy0FhXzW0TbSjJgMCOWAeNbWF8"
{"message":"this is login-required secret data","user":{"id":"testuser1","name":"testuser1","hashed_password":"$2b$12$m8uuHuIPrnvPdjuhEcTjWupCltCFIxUHpOoOZ9/d3qSqrbZI.RQ9O"}}
```

