![Java](https://img.shields.io/badge/java-%23ED8B00.svg)
![opensource](https://camo.githubusercontent.com/97d4586afa582b2dcec2fa8ed7c84d02977a21c2dd1578ade6d48ed82296eb10/68747470733a2f2f6261646765732e66726170736f66742e636f6d2f6f732f76312f6f70656e2d736f757263652e7376673f763d313033)
# MessageService
## Overview

## Getting started
Message Service that can retrieve messages from authenticated user and store messages in DB.
## Developing
### Build with
- Java 17
- Maven
- Spring Boot
- JJWT
- PostgreSQL
### Prerequisites
- Java 17
- Maven
- PostgreSQL
### SettingUp Dev
Download ZIP archive via [link](https://github.com/Soddik/MessageService/archive/refs/heads/master.zip).

Download project from repository via HTTPS:
`git clone https://github.com/Soddik/MessageService.git`.

### Building & Deploying
Build JAR file: in project directory run `./mvnw package -DskipTests` via CLI.

Build JAR file and deploy in Docker: in project directory run `docker-compose up -d --build` via CLI.

## API documentation
### Endpoints
| Endpoint              | Request Type | Auth.required * | Request body example                                    | Response code |              Description |
|-----------------------|:------------:|-----------------|---------------------------------------------------------|---------------|-------------------------:|
| api/v1/users/         |     POST     | +               |                                                         | 200           |    Returns list of users |
| api/v1/users/register |     POST     |                 | `{"name":"name_example","password":"password_example"}` | 201           |        Register new user |
| api/v1/auth/login     |     POST     |                 | `{"name":"name_example","password":"password_example"}` | 200           |              Returns jwt |
| api/v1/message        |     POST     | +               | `{"name":"name_example","content":"msg_example"}`       | 201           | Logs an incoming message |

*authentication via jwt

If you send `{"name":"name_example","content":"history X"}`(where X is integer) on `api/v1/message` last X messages will be response

The header of requests to be authorized must begin with Bearer_

### User requests
User registration: 

`curl -d '{"name":"user_name_example", "password":"password_example"}' -H "Content-Type: application/json" -X POST http://localhost:8081/api/v1/users/register`

Login: 

`curl -d '{"name":"user_name_example", "password":"password_example"}' -H "Content-Type: application/json" -X POST http://localhost:8081/api/v1/auth/login`

Retrieve list of users: 

`curl -d '{"name":"user_name_example", "password":"password_example"}' -H "Content-Type: application/json" -H "Authorization: Bearer_{token}" -X POST http://localhost:8081/api/v1/users/`

### Message requests
Send message:

`curl -d '{"name":"user_name_example", "message":"message_example"}' -H "Content-Type: application/json" -H "Authorization: Bearer_{token}" -X POST http://localhost:8081/api/v1/message/`

Retrieve last X messages:

`curl -d '{"name":"user_name_example", "message":"history {X}"}' -H "Content-Type: application/json" -H "Authorization: Bearer_{token}" -X POST http://localhost:8081/api/v1/message/`
## Usage
1. Register user
2. Retrieve jwt via login request
3. Send message
