## Get Home Data

Endpoint : GET /api/home

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": {
    "totalBalance": -25321.0,
    "totalIncomeThisMonth": 0.0,
    "totalExpenseThisMonth": 45321.0,
    "lastTransactions": [
      {
        "id": "4694d68b-88c3-4a9e-839e-ef85141734c9",
        "type": "EXPENSE",
        "date": "2024-12-02",
        "amount": 25000.0,
        "description": "Gojek",
        "account": {
          "id": "b548b727-77f0-440a-a6d0-6a9776909577",
          "name": "Cash",
          "balance": -25321.0
        },
        "category": {
          "id": "b7918c3e-ddae-47d4-82a6-e33aa486af5b",
          "name": "Transportation",
          "type": "EXPENSE"
        }
      },
      {
        "id": "68af8297-9dfc-469d-93ac-62bcccc7240d",
        "type": "EXPENSE",
        "date": "2024-12-02",
        "amount": 5321.0,
        "description": "Beli tepak",
        "account": {
          "id": "b548b727-77f0-440a-a6d0-6a9776909577",
          "name": "Cash",
          "balance": -25321.0
        },
        "category": {
          "id": "1bfea074-50f4-4131-8f69-c47a053d7791",
          "name": "Apparel",
          "type": "EXPENSE"
        }
      },
      {
        "id": "dc607565-f0ed-4990-8343-ec89d722ba26",
        "type": "EXPENSE",
        "date": "2024-12-02",
        "amount": 15000.0,
        "description": "Beli tepak",
        "account": {
          "id": "b548b727-77f0-440a-a6d0-6a9776909577",
          "name": "Cash",
          "balance": -25321.0
        },
        "category": {
          "id": "1bfea074-50f4-4131-8f69-c47a053d7791",
          "name": "Apparel",
          "type": "EXPENSE"
        }
      }
    ]
  },
  "errors": null
}
```