# T1_test task
This repository contains an application that is the solution to the task published here: 
https://docs.google.com/document/d/1G_bNiKfEWXorOOry79s7UzPTNvYy4tRaYqnUnhPxuiU/edit

## Description
This application counts how many times a character is presented in an input string. The result is shown as a table.

The application uses a primitive visual interface for string input and for counter output.

## Constrains
A string has to satisfy the constraints below:

1) Size: form 1 to 255 characters (blank strings are not processed);
2) Strings may contain only letters, digits, underscores, dashes and dots.

## Used technologies
* Spring Boot;
* Thymeleaf
* JUnit
* MockMvc
* Maven

__Attention: for simplification, the app does not use any database and does not store any data!__
## Requirements

* JDK 17+
* Maven (the latest version is recommended)

## Setup and run
__*In console:*__
```zsh
mvn compile
```
And then
```zsh
mvn exec:java -Dexec.mainClass="com.goltsov.test_t1.TestT1Application"      
```
You can also use `IntelliJ IDEA` or other IDEs for running the app.

Then open `http://localhost:8080` in your browser and use the app.
