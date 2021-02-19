# Introduction 
retail-store:- Retail website that given a bill, it finds the net payable amount.
This application is for calculate discount for retail store according to different client types, item types, and bill total amount.

## Installation processes
This maven project can be used by any IDE just clone the project and open it as a maven project.
To install and run test cases use the following maven command-line
```
mvn clean install
```
To create code cover report [You can find this report in the target/site/jacoco directory]
```
mvn test jacoco:report
```
## Tools
```
- spring boot 
- Junit5 Mockito 
- jacoco
```
## API references
```
localhost:8080/swagger-ui.html
```
URL
```
localhost:8080/v1/bill/discount
```
request
```
{
  "customerDetails": {
    "affiliate": true,
    "employee": false,
    "name": "mostafa",
    "registrationDate": "2019-07-06T11:16:20.385Z"
  },
"items": [
    {
      "grocery": true,
      "name": "Apple",
      "price": 100,
      "quantity": 4
    },{
      "grocery": false,
      "name": "Mobile",
      "price": 150,
      "quantity": 4
    }
  ]
}
```
response
```
{
  "customerDetails": {
    "name": "mostafa",
    "registrationDate": "2019-07-06T11:16:20.385+0000",
    "employee": false,
    "affiliate": true
  },
  "totalAmount": 1000,
  "percentageDiscount": "10%",
  "discountPerAmount": 45,
  "totalDiscount": 105,
  "netAmount": 895
}
```