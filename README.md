# UNote — Backend

UNote is a collaborative note-taking and note-sharing platform built in 24 hours at a hackathon. This repository contains the Spring Boot backend, which handles user management, note storage, sharing permissions, and file uploads via Cloudflare R2.

> Built under a 24-hour time constraint, demonstrates rapid prototyping, scoped decision-making, and delivery under pressure.

---

## Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![Cloudflare R2](https://img.shields.io/badge/Cloudflare_R2-F38020?style=flat&logo=cloudflare&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)

---

## Features

- User account management
- Note creation, editing, and deletion
- Note sharing between users
- File/image attachment support via Cloudflare R2 object storage
- Persistent storage with a relational database

---

## Running Locally

**Prerequisites:** Java 17+, Maven, a Cloudflare R2 bucket

```bash
git clone https://github.com/ArjavTripathi/UNote-Backend
cd UNote-Backend
```

Set the following environment variables or update `src/main/resources/application.properties`:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=

cloudflare.r2.access-key=
cloudflare.r2.secret-key=
cloudflare.r2.account-id=
cloudflare.r2.bucket=
cloudflare.r2.public-url=
```

Then run:

```bash
mvn spring-boot:run
```

Server starts at `http://localhost:8080`
