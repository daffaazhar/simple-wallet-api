## Get All Accounts

Endpoint : GET /api/account

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": {
    "assets": 0.0,
    "debts": -321.0,
    "total": -321.0,
    "accounts": [
      {
        "id": "b548b727-77f0-440a-a6d0-6a9776909577",
        "name": "Cash",
        "balance": -321.0
      }
    ]
  },
  "errors": null
}
```

## Get Account by Id

Endpoint : GET /api/account/{accountId}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": {
    "id": "b548b727-77f0-440a-a6d0-6a9776909577",
    "name": "Cash",
    "balance": 25321.0
  },
  "errors": null
}
```

## Create Account

Endpoint : POST /api/account

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name": "Cash",
  "balance": 25321
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "b548b727-77f0-440a-a6d0-6a9776909577",
    "name": "Cash",
    "balance": 25321.0
  },
  "errors": null
}
```

## Delete Account

Endpoint : DELETE /api/account/{idAccount}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": "OK",
  "errors": null
}
```