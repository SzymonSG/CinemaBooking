## Cinema Reservation project.
*The created project can be divided into 3 main functionals parts:*   
- The first part one is an API responsible for **booking seats na movie** which is supporting by **(API-s)** which returns **info about movies** with the use **Query Methods**, and **JPQL**.
- Second is an **API** responsible for the **registration of the user**, along with the entire range of functionalities like: **(account activation via a link, password reset, change password, request to resend the activation link, etc.)**
- The third is **the process of logging**, supporting by **Spring Security** and authoritzation **JWT**.
## Description few important endpoints:

#### Booking seats on movie
*to perform action requierd Jwt token in haeder with key: "Authorization"*
```http
  PUT /reservations
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `RequestBody` | `ReservationModelDto `| **All below- required:**|
| `cinemaName` | `String `| Name of cinema.|
| `movieName` | `String `|  Name of the movie to booked.|
| `dateAndTime` | `LocalDateTime `| **pattern** ="yyyy-MM-dd; HH:mm:ss*" Day and hour playing movie.|
| `seatsToBooked` | `List <Intger> `|User can book **multiple seats** for chosen movie.|



#### Sign up 
*After successful regitration click in the activation link. To be able to perform any actions on your account.*

```http
  GET /api/register
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `RequestBody`      | `UserModelDto` |**All  below- required:** |
| `firstName`      | `String` | First name user |
| `lastName`      | `String` | Last name  user |
| `email`      | `String` | **@** symbol is requierd  |               
| `password`      | `String` | Password:  ***min length**=8*, needs ***least one***: *upper and lower case, digit, special-char*|



#### Sign in 

```http
  GET /api/logging
```
*After successful loggin, application generete for User **"Jwt token"**, which is need to perform: [booking seats on movie.](#booking-seats-on-movie)* 
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `RequestBody`      | `LoginModel` |**All  below- required:** |
| `email`      | `String` | *Email from regitration*, **@** symbol is requierd |
| `password`      | `String` | *Password from regitration* |


#### Show repertoire 

```http
  GET /api/repertoire/cinemas/{cinemaName}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `cinemaName`      | `String` | **Requierd** to fetch repertoire form cinema|


#### Show avialable seats: 

```http
  GET api/cinemas/{cinemaName}/movies/{movieName}/avialable-seats
```
*This api supporting info about: [booking seats on movie.](#booking-seats-on-movie)*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |                            
| `cinemaName`      | `String` | **Requierd** to selected cinema|
| `movieName`      | `String` | **Requierd** to fetch avialable seats on selected movie|



## Validation:
In overview short:
- @CrontrolerAdvice addnotation to global exception handling. 
- Hibernate validtion in DTO-s layers.
- To resolve cross-cutting concerns like: user registration: AspectJ library from the AOP approach.
- Own validation movie-seats during reservation, jwt token validation and more.

## Mapstruct:
To applied DTO pattern I have used  Mapstruct & Lombok tools.  *To read more:* https://mapstruct.org/

## Fetching data from DB using JPA and Hibernate

- Query methods
- **JPQL** queries with params 



### Using dependencies to create app:

- Spring Web
- Spring Data JPA
- Validation I/O
- Spring Boot Dev-Tools
- MapStruct 
- Lombok
- H2 database -testing
- PostgreSQL Driver
- Guava
- AspectJ
- Spring Security
- JWT


