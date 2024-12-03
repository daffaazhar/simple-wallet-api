## Get Home Data

Endpoint : GET /api/home

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": {
    "totalBalance": -321.0,
    "totalIncomeThisMonth": 0.0,
    "totalExpenseThisMonth": 20321.0,
    "lastTransactions": [
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
    ]
  },
  "errors": null
}
```