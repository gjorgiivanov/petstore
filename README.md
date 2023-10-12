# PetStore Application

This README provides step-by-step instructions on how to clone the PetStore repository from GitHub 
and run the application locally. The PetStore application is built using Spring Boot and Spring GraphQL. 
Before you begin, ensure you have the following prerequisites installed:
- Your favorite IDE (IntelliJ, Eclipse, ...)
- Java Development Kit (JDK) 17 or later
- Maven
- Docker or PostgreSQL

## Getting Started
Follow these steps to get the PetStore application up and running locally:

### 1. Clone or Download the Repository:
To clone the PetStore repository from GitHub, open your terminal and run the following command:  
`git clone https://github.com/gjorgiivanov/petstore.git`

### 2. Navigate to the Project Directory:
Change your current directory to the project's root directory:  
`cd petstore`

### 3. Configure the Database (PostgreSQL or Docker):
#### 3.1. PostgreSQL
If you prefer to run PostgreSQL locally, you can create the PetStore database manually. 
Assuming you have PostgreSQL installed and configured, you just need to create the database
and enter the needed data for it in the application-dev.properties file.

#### 3.2 Docker
An easier way to start the database is with Docker using the provided docker-compose.yml file. 
Run the configuration file with your IDE or execute the following command in the project directory:  
`docker-compose up -d`  
This will start a PostgreSQL container with the required configuration 
(username => user, password => password, and database name => petstore).
The database will be accessible on port 5000.

### 4. Build and Run:
Open the PetStore project in your integrated development environment (IDE) of choice then Build and Run the Application.

## Rest Controllers and GraphQL
### Rest Controllers
To reach the application's API endpoints use postman or similar program to make GET and POST calls.
Here are the endpoints for each of the Rest Controllers:
#### 1. UserRestController
- **GET /api/user/list-users**: Retrieves a list of users from the service.
- **POST /api/user/create-users**: Creates users based on the provided user DTOs. 
Allows for the creation of multiple users at once, 
if they are not provided they will be created at random.
- **GET /api/user/buy**: Performs an action where users buy pets. It may redirect to another endpoint 
(/api/history-log) after the action.

#### 2. HistoryLogRestController
- **GET /api/history-logs**: Retrieves a list of purchase history logs, 
providing a historical record of pet purchases.

#### 3. PetRestController
- **GET /api/pet/list-pets**: Retrieves a list of pets from the service.
- **POST /api/pet/create-pets**: Allows for the creation of pets based on provided pet DTOs. 
It supports the creation of multiple pets simultaneously,
if they are not provided they will be created at random.

### GraphQL
Open your web browser and navigate to http://localhost:8080/graphiql to access the GraphQL Playground. 
Here, you can interact with the GraphQL API and explore the available queries and mutations.  
#### For example:
_List Users:_
```
query {
  users {
    firstName
    lastName
    emailAddress
    budget{
      amount
      currency
    }
  }
}
```
_List Pets:_
```
query {
  pets {
    name
    type
    description
    dateOfBirth
    price {
      amount
      currency
    }
    rating
    owner {
      firstName
      lastName
    }
  }
}
```
_Creating Users:_
```
mutation {
  createUsers(
    userCreationDTOs: [
      {
        firstName: "First",
        lastName: "User",
        emailAddress: "first.user@mail.com",
        budget: {
          amount: 11,
          currency: USD
        }
      }
    ]
  ) {
    firstName
    lastName
    emailAddress
    budget{
      amount
      currency
    }
  }
}
```
_Buying pets for users:_
```
mutation {
  buy {
    dateOfExecution
    numberOfSuccessfulBuyers
    numberOfUnsuccessfulBuyers
  }
}
```
_Creating Pets:_
```
mutation {
  createPets(petCreationDTOs: null) {
    name
    type
    dateOfBirth
    rating
    owner {
      firstName
      lastName
    }
  }
}
```

## Tests
The service layer within the petstore project, which encapsulates the core business logic and functionality,
is thoroughly covered with unit tests. These unit tests were created with junit and mockito and can be 
seen and run in the test folder of the project.

## Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#web)

## Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
