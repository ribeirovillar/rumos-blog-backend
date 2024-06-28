# Rumos' Blog Project

The Rumos' blog project is a backend application developed using Java, Spring Boot, and SQL. It is a Maven project that
uses PostgreSQL as its database, managed through Docker and pgAdmin.

The application provides two types of user roles: Admin and Common User. The credentials for these users are provided in
the README.md file. The application uses JWT for authentication, and the tokens have an expiration time that can be
configured.

The project includes SQL migration files that insert initial data into the database, including user and role
information. The SQL scripts are organized in a way that they create roles first, then users, and finally assign roles
to the users.

---

## Running the Application

There are three ways to start the application:

### Option 1: Using the Shell Script

A shell script is provided in the root directory of the project to automate the process of cloning the repositories and starting the projects.

#### Requirements
- docker-compose
- Java 17
- Maven
- git
- docker
- npm

#### Steps to Run the Application

1. Download the [`start-project.sh`](https://github.com/ribeirovillar/rumos-blog-backend/blob/master/start-project.sh)  script to your desired directory.
2. Open a terminal in the directory where the script was downloaded.
3. Run the following command to give execution permissions to the script:
```bash
chmod +x start-project.sh
```
4. Run the script with the following command:
```bash
./start-project.sh
```

### Option 2: Using Docker Compose

You can use Docker Compose to start the application:

#### Requirements
- Docker-compose
- Java 17
- Maven

#### Steps to Run the Application

1. Open a terminal in the root directory of the project.
2. Clone the repository:
```bash
git clone https://github.com/ribeirovillar/rumos-blog-backend.git
```
3. Navigate to the project directory:
```bash
cd rumos-blog-backend
```
4. Run the following command to build and start the containers defined in your `docker-compose.yml`:
```bash
docker-compose up -d
```

### Option 3: Manual Setup

If you prefer to set up the project manually, follow these steps:

#### Requirements
- Java 17
- Maven
- Docker
- Docker-compose
- git

#### Steps to Run the Application

1. Open a terminal in the root directory of the project.
2. Clone the repository:
```bash
git clone https://github.com/ribeirovillar/rumos-blog-backend.git
```
3. Navigate to the project directory:
```bash
cd rumos-blog-backend
```
4. Start the PostgreSQL database and pgAdmin application using Docker:
```bash
docker-compose up -d postgres pgadmin
```
5. Build the project with Maven:
```bash
mvn clean install
```
6. Run the application:
```bash
java -jar target/blog.jar
```

---

## User Application Credentials

| Role  | Email           | Password  |
|-------|-----------------|-----------|
| Admin | admin@fake.com  | password  |
| User  | user@fake.com   | Senha@00  |

---

# Swagger

The application's API can be explored and tested using Swagger, accessible
at http://localhost:8080/swagger-ui/index.html

---

# Postman Collection

A [Postman collection](postman_data.zip) is provided in the root directory of the project, which includes scripts to auto-generate access
tokens for each request based on environment variables.


---

# pgAdmin

## Database management tool.

- URL: http://localhost:5050

- Username: email@fake.com

- Password: password

Configure the database connection using the following steps:
![Go to Object in the menu bar, then Register -> Server...](src/main/resources/images/pgadmin/Xnip2024-05-05_14-59-41.jpg)
![Type a name connection and then go to Connection tab](src/main/resources/images/pgadmin/Xnip2024-05-05_15-00-38.jpg)
![Fill in the form connection using the details found in the docker-compose.yml file](src/main/resources/images/pgadmin/Xnip2024-05-05_15-04-33.jpg)