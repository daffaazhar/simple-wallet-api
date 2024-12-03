## Get Transaction by Month and Year

Endpoint : GET /api/transaction?month={month}&year={year}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "id": "68af8297-9dfc-469d-93ac-62bcccc7240d",
      "accountId": "b548b727-77f0-440a-a6d0-6a9776909577",
      "categoryId": "1bfea074-50f4-4131-8f69-c47a053d7791",
      "type": "EXPENSE",
      "date": "2024-12-02",
      "amount": 5321.0,
      "description": "Beli tepak"
    },
    {
      "id": "dc607565-f0ed-4990-8343-ec89d722ba26",
      "accountId": "b548b727-77f0-440a-a6d0-6a9776909577",
      "categoryId": "1bfea074-50f4-4131-8f69-c47a053d7791",
      "type": "EXPENSE",
      "date": "2024-12-02",
      "amount": 15000.0,
      "description": "Beli tepak"
    }
  ],
  "errors": null
}
```

## Create Transaction

Endpoint : POST /api/transaction

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "accountId": "b548b727-77f0-440a-a6d0-6a9776909577",
  "categoryId": "b7918c3e-ddae-47d4-82a6-e33aa486af5b",
  "type": "EXPENSE",
  "date": "2024-12-02",
  "amount": 25000,
  "description": "Gojek"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "4694d68b-88c3-4a9e-839e-ef85141734c9",
    "accountId": "b548b727-77f0-440a-a6d0-6a9776909577",
    "categoryId": "b7918c3e-ddae-47d4-82a6-e33aa486af5b",
    "type": "EXPENSE",
    "date": "2024-12-02",
    "amount": 25000.0,
    "description": "Gojek"
  },
  "errors": null
}
```

## Delete Transaction

Endpoint : DELETE /api/transaction/{transactionId}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": "OK",
  "errors": null
}
```