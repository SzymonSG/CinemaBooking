## Cinema Reservation project.
*The created project can be divided into 3 main functionals parts:*   
- The first part one is an **(Command API- PUT)** serving **booking seats na movie** which is supporting by **(Query API- GET)** which returns **info about movies**.
- Second is an **API** responsible for the **registration of the user**, along with the entire range of functionalities like: **(account activation via a link, password reset, change password, request to resend the activation link, etc.)**
- The third is **the process of logging**, supporting by authoritzation**JWT**


- ![#1589F0](https://via.placeholder.com/15/1589F0/000000?text=+) `testcolor` dadas






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



#### Sing up User 
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



#### Sing in User

```http
  GET /api/login
```
*After successful loggin, application generete for User **"Jwt token"**, which is need to perform: [booking seats on movie.](#booking-seats-on-movie)* 
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `RequestBody`      | `LoginModel` |**All  below- required:** |
| `email`      | `String` | *Email from regitration*, **@** symbol is requierd |
| `password`      | `String` | *Password from regitration* |



#### Show repertoire 

```http
  GET /repertoire/cinemas/{cinemaName}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `PathVariable`      | `String` |**All  below- required:** |
| `cinemaName`      | `String` | Name of cinema from which we want the repertoire |

## Roadmap

- 1. Part of Validation work from DTO-s layers. To avoid mixed with DB logic

- 2. Validtion "Unique values" and requierments for password are performed by AspectJ.
**it was needed to not mixed with services logic and not conflicts with Becrypt converter, wchich allows all type passwords :)"

- 3. Custom validtion seats to booked.

- 4. Validation JWT token, and token from verify links activation

- 5. Using AOP @ControllerAdvice for exception handling form controllers.


