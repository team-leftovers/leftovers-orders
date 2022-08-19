# leftovers-orders

Order Creation/Update DTO (JSON)
<br>
```json
{
    "driverId": integer,
    "customerId": integer,
    "restaurantId": integer,
    "discountId": integer,
    "status": "enum string"
    "price": BigDecimal
}
```
Valid values for status are "pending", "accepted", "working", "waiting", "delivery", "delivered", "cancelled", and "error".

main endpoint /orders
POST:
- Creates a new order using a JSON object. The selected Ids for driver, customer, restaurant, and discount must represent extant entities in the database

GET:
- Returns a list of all orders
 
orders/{id}
GET:
- returns a JSON containing all information for the order with selected id

PUT:
- Updates an order using a JSON object. The selected Ids for driver, customer, restaurant, and discount must represent extant entities in the database

DELETE:
- Deletes the selected order

orders/{id}/Driver
GET:
- returns information about the driver associated with the selected order

orders/{id}/Customer
GET:
- returns information about the customer associated with the selected order

orders/{id}/Restaurant
GET:
- returns information about the restaurant associated with the selected order

orders/{id}/Address
GET:
- returns information about the address of the restaurant associated with the selected order
 
orders/{id}/Price
GET:
- returns the total price associated with the selected order

