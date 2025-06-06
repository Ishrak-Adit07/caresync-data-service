# Data Service

## Base URL for Local Development

```
http://localhost:8082/
```

## Base URL for Production Environment

```
TBD
```

# Hospital API v1

## Base URL

```
/hospital/v1
```

---

## 1. Test User Service

**GET** `/test`

### Description

Simple endpoint to check if the Hospital service is running properly.

### Success Response – 200

```json
"Hospital service running successfully"
```

---

## 2. Get All Hospitals

**GET** `/all`

### Description

Fetches all available hospitals in the system.

### Success Response – 200

```json
[
  {
    "id": 1,
    "name": "Green Life Hospital",
    "phoneNumber": "0123456789",
    "website": "https://greenlife.com",
    "types": ["PRIVATE", "SPECIALIZED"],
    "icus": 10,
    "locationResponse": null
  },
  {
    "id": 2,
    "name": "Apollo Hospital",
    "phoneNumber": "0987654321",
    "website": "https://apollo.com",
    "types": ["PUBLIC"],
    "icus": 15,
    "locationResponse": null
  }
]
```

### Error Response – 500

```json
{
  "success": false,
  "error": {
    "code": "SERVER_ERROR",
    "message": "An unexpected error occurred while retrieving locations."
  }
}
```

---
