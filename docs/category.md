## Get All Categories

Endpoint : GET /api/category

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "id": "e2e2507f-7940-46d8-8416-f9b425a6315c",
      "name": "Apparel",
      "type": "INCOME"
    }
  ],
  "errors": null
}

```

## Create Category

Endpoint : POST /api/category

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name": "Apparel",
  "type": "INCOME"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "e2e2507f-7940-46d8-8416-f9b425a6315c",
    "name": "Apparel",
    "type": "INCOME"
  },
  "errors": null
}

```

## Update Category

Endpoint : PUT /api/category/{idCategory}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name": "Fashion"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "e2e2507f-7940-46d8-8416-f9b425a6315c",
    "name": "Fashion",
    "type": "INCOME"
  },
  "errors": null
}

```

## Delete Category

Endpoint : DELETE /api/category/{idCategory}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": "OK",
  "errors": null
}
```