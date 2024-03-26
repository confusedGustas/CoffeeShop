
# CoffeeShop Application

A simple CoffeeShop application built with Spring Boot, PostgreSQL, Angular, maildev, Sessions and CSRF tokens.

## Features

- Admin Login
- Product listing, editing and deletion
- About Us and Contact page customization
- JSESSIONID based authentication
- CSRF tokens for XSS attacks
- Contact Us trough email option

## Prerequisites

Make sure you have the following tools installed before setting up the application:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)
- [maildev](https://github.com/maildev/maildev)

## Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/confusedGustas/CoffeeShop
    cd CoffeeShop
    ```

2. **Configure PostgreSQL:**

   - Create a new database (coffeeshop)
   - Leave the default settings as it is, Spring Boot will create the needed tables.
   - If you want configurate the settings, you can do it so by accessing the `application.yaml` file

    ```datasource:
    url: jdbc:postgresql://localhost:5432/coffeeshop
    username: postgres
    password: postgres
    ```

3. **Build and run the application:**

    ```bash
    npm install
    npm i maildev
    maildev
    mvn spring-boot:run
    cd frontend
    ng serve
    ```

   - Spring Boot backend will start on `http://localhost:8080`
   - Angular frontend on `http://localhost:4200`
   - maildev  on `http://localhost:1080`

## Usage

- Access the application through your [browser](http://localhost:4200), Login with the default credentials (Admin/Admin). The password can be changed through the browser.
- [Contact](http://localhost:4200/contact), [About](http://localhost:4200/about) and [Home](http://localhost:4200) pages will be empty by default. You need to log in and edit these areas through the admin panel.
- [Contact](http://localhost:4200/contact) page will contain a "Contact us" option. To test it out, fill out the blanks and visit [maildev](`http://localhost:1080`) to check out the received message.

## License

This project is licensed under the [MIT License](./LICENSE).