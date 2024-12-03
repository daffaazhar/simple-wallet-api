## Get Expense Analytic

Endpoint : GET /api/analytic/expense?month={month}&year={year}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "category": "Transportation",
      "totalAmount": 25000.0,
      "percentage": 55.16206615034973
    },
    {
      "category": "Apparel",
      "totalAmount": 20321.0,
      "percentage": 44.83793384965028
    }
  ],
  "errors": null
}
```

## Get Income Analytic

Endpoint : GET /api/analytic/income?month={month}&year={year}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "category": "Transportation",
      "totalAmount": 25000.0,
      "percentage": 55.16206615034973
    },
    {
      "category": "Apparel",
      "totalAmount": 20321.0,
      "percentage": 44.83793384965028
    }
  ],
  "errors": null
}
```