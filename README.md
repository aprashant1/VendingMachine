# VendingMachine
Sample API's using springBoot

## PROBLEM STATEMENT
```
ï»¿testTaskSSE
Create a new project on Github with the implementation of the assignment below.

Create Spring Boot REST API application that is supposed to emulate simplified vending machine. Item types that can be inserted can be of âSodaâ, âCandyâ or âToyâ type. Other types are not allowed. The capacity for each item type is 10 items. Implement these endpoints:

Add an item into the vending machine
request
POST /api/deposit
{   
    "type": "Soda"
}

Response if the item was successfully added:
response
{
    "id": 1,
    "status": "OK"
}

Response if the machine is out of capacity or is of an incorrect type:
depositErrorResponse
{    
    "status": "Error code for the given situation"
}
Withdraw item from the machine by type
request
POST /api/withdraw
{   
    "type": "Soda"
}

Response if the vending machine contains this item: 
response
{
    "status": "OK"
}

Response if the vending machine does not contain this item: 
response
{
    "status": "N/A"
}
Get list of all items
request
GET /api/getlist
response
[
    {
        "id": 1,
        "type": "Soda"
    },
    {
        "id": 2,
        "type": âCandyâ
    }
]
Application should:

have a build script (Gradle or Maven)
be n-tier application consisting of controller, service and storage layer
have unit tests for controller and service classes
have integration tests
correctly handle exceptions
```

