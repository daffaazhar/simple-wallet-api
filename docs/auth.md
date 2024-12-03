## Register User

Endpoint : POST /api/auth/register

Request Body :

```json
{
  "name": "Daffa",
  "email": "daffa@gmail.com",
  "password": "daffa123"
}
```

Response Body (Success) :

```json
{
  "data": "OK",
  "errors": null
}

```

Response Body (Failed) :

```json
{
  "errors": "email: must not be blank, name: must not be blank"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "email": "daffa@gmail.com",
  "password": "daffa123"
}
```

Response Body (Success) :

```json
{
  "data": {
    "token": "6dc09eb2-760f-4394-be9a-46c9f9ea885b",
    "expiredAt": 1733247374701
  },
  "errors": null
}

```

Response Body (Failed) :

```json
{
  "errors": "Email or password wrong"
}
```

## Me

Endpoint : POST /api/auth/me

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": {
    "name": "Daffa",
    "email": "daffa@gmail.com"
  },
  "errors": null
}

```

Response Body (Failed) :

```json
{
  "data": null,
  "errors": "Unauthorized"
}
```