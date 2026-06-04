# UNote — Backend

UNote is a collaborative note-taking and note-sharing platform built in 24 hours at a hackathon. This repository contains the Spring Boot backend, which handles user accounts, note and file management, PDF commenting, video timestamp annotations, class/unit organization, and file uploads via Cloudflare R2.

> Built under a 24-hour time constraint, which demonstrates rapid prototyping, scoped decision-making, and delivery under pressure.

---

## Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat&logo=spring-security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=flat&logo=JSON%20web%20tokens)
![Cloudflare R2](https://img.shields.io/badge/Cloudflare_R2-F38020?style=flat&logo=cloudflare&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)

---

## Features

- User registration, login, profile update, and account deletion
- JWT-based authentication and secured routes
- Note creation with file upload (PDF, images) via Cloudflare R2
- Text-based note entry support
- PDF annotation with threaded comments (create, read, update, delete)
- Video timestamp annotations on notes (create, read, update, delete)
- Class and unit organization for note grouping
- Shared note links between users

---

## Architecture

Standard layered Spring Boot architecture:

- **Controller Layer** — REST endpoints organized by domain
- **Service Layer** — business logic per feature area
- **Repository Layer** — JPA repositories per entity
- **Security Config** — JWT filter chain with stateless session management
- **R2Service** — Cloudflare R2 integration for file storage

---

## API Reference

### Auth — `{api.prefix}/auth`

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | `/auth/login` | None | Login and receive JWT |
| POST | `/auth/register` | None | Register a new account |

### Accounts — `{api.prefix}/accounts`

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| GET | `/accounts/me` | JWT | Get authenticated user's account |
| PUT | `/accounts/update` | JWT | Update account details |
| DELETE | `/accounts/delete` | JWT | Delete authenticated user's account |

### Notes — `/app/v1/notes`

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| GET | `/notes` | JWT | Get notes by filter |
| POST | `/notes` | JWT | Upload a note with file (multipart) |
| GET | `/notes/all` | None | Get all notes |
| DELETE | `/notes` | JWT | Delete a note by ID |
| POST | `/notes/textentry` | JWT | Create a text-based note entry |

### PDF Comments — `/notes/{notesId}/pdfs-comments`

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | `/pdfs-comments/create` | JWT | Add a comment to a PDF note |
| GET | `/pdfs-comments/get/all` | None | Get all comments for a note |
| PUT | `/pdfs-comments/{commentId}/update` | JWT | Update a comment |
| DELETE | `/pdfs-comments/{commentId}/delete` | JWT | Delete a comment |

### Video Timestamps — `/notes/{notesId}/timestamps`

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | `/timestamps/add` | JWT | Add a timestamp to a video note |
| GET | `/timestamps/get/all` | None | Get all timestamps for a note |
| PUT | `/timestamps/{commentId}/update` | JWT | Update a timestamp |
| DELETE | `/timestamps/{commentId}` | JWT | Delete a timestamp |

### Classes — `/app/v1/classes`

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| GET | `/classes` | None | Get a class by name |
| POST | `/classes` | None | Create a new class |
| GET | `/classes/all` | None | Get all classes |

### Units — `/app/v1/units`

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| GET | `/units` | None | Get a unit |
| POST | `/units` | None | Create a unit |
| DELETE | `/units` | None | Delete a unit |
| GET | `/units/all` | None | Get all units |

### Links — `/app/v1/links`

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | `/links` | JWT | Create a shared note link |
| GET | `/links/{id}` | None | Get a link by ID |
| GET | `/links/unit/{unitId}` | None | Get all links for a unit |
| DELETE | `/links/{id}` | JWT | Delete a link |

---

## Running Locally

**Prerequisites:** Java 17+, Maven, a Cloudflare R2 bucket

```bash
git clone https://github.com/ArjavTripathi/UNote-Backend
cd UNote-Backend
```

Set the following in `src/main/resources/application.properties`:

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
