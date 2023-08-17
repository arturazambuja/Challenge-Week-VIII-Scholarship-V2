# Challenge-Week-VIII-Scholarship

Design of a RESTful API developed with Spring Boot 3.0.9, Java 17 and Maven. This project uses HTTP operations (GET, POST, PUT and DELETE) to manage the Scholarship. In this project we have the possible actions to create Classrooms, Students, Coordinators, Instructors and Scrum Masters, Squads are automatically generated when starting a class.
In this project I chose to use MySQL as a Database seeing that it would be a great option to meet the needs of the project.

Classes must have at least 15 students, 1 coordinator, 1 scrum master and 3 instructors to be created and must not exceed 30 students. For the squads to be created, it is enough for the class to be started, the squads are automatically generated and the students divided between them in the most balanced way possible with a deck-style logic.
Hibernate ddl auto setted by create-drop.

## Features
* Model Mapper 2.4.4
* Lombok
* Spring Boot DevTools
* Spring Web
* Spring Data JPA
* MySQL Driver SQL
* Validation
* Spring Boot Starter Test

## Installation

```git clone https://github.com/arturazambuja/Challenge-Week-VIII-Scholarship-V2```

```cd Scholarship```

## Prerequisites
- Spring Boot 3.0.9
- Maven
- Java 17
- MySQL
- IntelliJ IDEA