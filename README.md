### Registration Service Rest Api

Registration Service that implements a /register endpoint taking a JSON body.

#### Tech Stack Used

* JDK 8 or later
* Maven 3.0 or later
* Spring Boot 2.4 or later
* H2 in memory database -- (To store user registration details for valid requests)
* Hazelcast in memory database

### API Detail

* Required data & validation:

```
· Username - alphanumeric, no spaces
· Password – min length 4, at least one upper case letter & number
· DoB (Date of Birth) - ISO 8601 format
· Payment Card Number – between 15 and 19 digits
```

* Endpoint provided by the api:

```
 POST /registration-service/v1/register: register a new user to the system
```

* Expected Responses:

```
· If the request body fails to conform to any of the basic validation checks return HTTP Status code: 400
· Reject registrations if the user is under the age of 18 and return   HTTP Status code: 403
· If the username has already been used reject the request and return  HTTP Status code: 409
· A successful registration should return HTTP Status code: 201

```



#### Request and Responses for the endpoint
* Register User Sample Request

Request:
```
curl --location --request POST 'http://localhost:9080/registration-service/v1/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "ABC123456",
    "password": "Pass12",
    "dob": "1985-02-21",
    "paymentCardNumber": "200001081054424567"
}'
```

Response:
```
201 CREATED
```


### Running test cases and application

#### Running test cases

* To run junit test cases

```
mvn clean verify or mvn clean test
```

#### Run locally as Spring-Boot application

```
mvn spring-boot:run
```


### Health Check
* It will provide the status if api is up and running:

```
http://localhost:9080/registration-service/v1/actuator/health
```

## Note: 
To provide blocked payment issuer identification numbers at application startup, 
I have created a custom class which will generate random 6 digits IIN between 100000 and 100010.
So to test blacklisted card number we can use card numbers starting with first 6 digit between 100000 and 100010.

#### Hazelcast is used as in memory distributed caching to store IIN on application startup, and also do not load IIN from DB or external service for each call to /register endpoint. 
#### Added a Spring Scheduler to refresh the cache at given time interval based on requirement.

* Blacklisted Payment Card Number Example:
```
1000081234567
```

* Sample Request for blacklisted iin test:

```
curl --location --request POST 'http://localhost:9080/registration-service/v1/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "ABC123456",
    "password": "Pass12",
    "dob": "1985-02-21",
    "paymentCardNumber": "100001081054424567"
}'
```

* Sample Response:

406 Not Acceptable

```
{
    "timestamp": "2021-04-11T18:17:49.087Z",
    "message": "Payment card number is blacklisted. Please use another card for registration",
    "details": "uri=/registration-service/v1/register"
}
```
