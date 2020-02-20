# rest-nisum
###### Ejercicio REST para empresa NISUM CHILE

### Prerequisitos
- JDK 1.8+  
- Gradle

### Stack:
* Spring Boot
* Spring Data JPA
* H2 (mem)
* STS 4
* Postman


### 1. Create User REST API

HTTP Method: POST 
Request URL: http://localhost:8085/api/v1/users

### 2. Get User by ID REST API
HTTP Method: GET 
Request URL: http://localhost:8085/api/v1/users/c7d5ae96-276b-427d-b0e0-3facb7fb5ed4

### 3. Update User by ID REST API
HTTP Method: PUT 
Request URL: http://localhost:8085/api/v1/users/c7d5ae96-276b-427d-b0e0-3facb7fb5ed4

### 4. Get All Users REST API
HTTP Method: GET 
Request URL: http://localhost:8085/api/v1/users

### 5. Delete User by ID REST API
HTTP Method: DELETE 
Request URL: http://localhost:8085/api/v1/users/c7d5ae96-276b-427d-b0e0-3facb7fb5ed4


### Para probar en Posman

1 -  En un terminal ejecutar el siguiente comando desde la raiz del proyecto: ./gradlew bootRun (o importarlo desde el IDE correspondiente, Eje: STS o Eclipse )

2 - Automaticamente se creara la base de datos con un usuario de prueba.

3 - Para ver la base de datos en el navegar ir a la siguiente url: http://localhost:8085/h2
