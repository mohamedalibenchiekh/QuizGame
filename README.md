# QuizGame - A Distributed Multiplayer Quiz Application

QuizGame is a distributed system that manages a multiplayer quiz game, integrating gRPC for real-time communication, Kafka for event-driven processing, and MongoDB for persistent data storage. The system also provides a REST API Gateway for web browser access.

## Table of Contents
- [Features](#features)
- [System Architecture](#system-architecture)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Accessing the Application](#accessing-the-application)
- [API Documentation](#api-documentation)
- [Docker Deployment](#docker-deployment)
- [Troubleshooting](#troubleshooting)

## Features

- **Player Management**
  - Register players and store their details in MongoDB
  - Automatically remove inactive players (using MongoDB TTL)

- **Game Play**
  - Load quiz questions from the database
  - Play the game with real-time interactions

- **Response Evaluation**
  - Validate player answers
  - Update player scores in the MongoDB database

- **Game Analysis**
  - View statistics about users (connected users, scores, etc.)

- **Multiple Interface Support**
  - gRPC interface for high-performance service-to-service communication
  - REST API Gateway for web browser access

## System Architecture

The application follows a microservices architecture with the following components:

### gRPC Services
- **Player Management Service**: Handles player registration and management
- **Gameplay Service**: Manages quiz sessions and game logic

### Kafka Integration
- **Player Management Producer**: Sends player registration events to Kafka
- **Gameplay Producer**: Sends player answer events to Kafka
- **ScoreUpdate Consumer**: Validates answers and updates user scores
- **Statistics Consumer**: Tracks game statistics

### MongoDB Database
- **Quizzes Collection**: Stores quiz questions and answers
- **Players Collection**: Stores player information and scores

### REST API Gateway
- Translates HTTP/JSON requests to gRPC calls
- Enables web browser access to the application

## Technology Stack

- **Java 11** - Programming language
- **gRPC** - High-performance RPC framework
- **Kafka** - Distributed event streaming platform
- **MongoDB** - NoSQL database for persistent storage
- **Spring Boot** - Framework for REST API Gateway
- **Docker & Docker Compose** - Containerization and orchestration

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Docker and Docker Compose
- Git

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd QuizGame
```

### Build the Application

```bash
mvn clean package
```

### Run Locally

1. Start the application:

```bash
mvn exec:java -Dexec.mainClass="com.quizgame.Application"
```

2. Run a test client:

```bash
mvn exec:java -Dexec.mainClass="com.quizgame.client.TestClient"
```

## Accessing the Application

### gRPC Interface
The gRPC server runs on port 50051. You can use any gRPC client to connect to it.

### REST API Gateway
The REST API Gateway runs on port 8080. You can access it using any web browser or HTTP client.

- Web Interface: http://localhost:8080/
- API Endpoints:
  - `POST /api/players/register` - Register a new player
  - `GET /api/game/quiz` - Load quiz questions
  - `POST /api/game/submit-answer` - Submit an answer to a quiz question

## API Documentation

### Player Management API

#### Register Player
```
POST /api/players/register
Content-Type: application/json

{
  "username": "yourUsername",
  "password": "yourPassword"
}
```

Response:
```json
{
  "success": true,
  "message": "Player yourUsername registered successfully!"
}
```

### Game API

#### Load Quiz
```
GET /api/game/quiz
```

Response:
```json
{
  "success": true,
  "message": "Quiz loaded successfully",
  "data": [
    "What is the capital of France?",
    "Who wrote Romeo and Juliet?"
  ]
}
```

#### Submit Answer
```
POST /api/game/submit-answer
Content-Type: application/json

{
  "playerName": "yourUsername",
  "answer": "yourAnswer"
}
```

Response:
```json
{
  "success": true,
  "message": "Answer submitted successfully"
}
```

## Docker Deployment

### Build and Deploy with Docker Compose

1. Build the application:

```bash
mvn clean package
```

2. Build and start the Docker containers:

```bash
docker-compose up -d
```

This will start:
- MongoDB on port 27017
- Zookeeper on port 2181
- Kafka on port 9092
- QuizGame application (gRPC on port 50051, REST on port 8080)

3. Check container status:

```bash
docker-compose ps
```

### Stopping the Application

```bash
docker-compose down
```

## Troubleshooting

### Common Issues

1. **Port already in use**:
   - Make sure ports 50051 (gRPC) and 8080 (REST) are available
   - Check for running processes: `lsof -i :50051` or `lsof -i :8080`

2. **Docker build issues**:
   - Ensure the JAR file is built before building the Docker image: `mvn clean package`
   - Check Docker logs: `docker-compose logs quizgame`

3. **MongoDB connection issues**:
   - Verify MongoDB container is running: `docker-compose ps`
   - Check MongoDB logs: `docker-compose logs mongo`

4. **Kafka connection issues**:
   - Verify Kafka container is running: `docker-compose ps`
   - Check Kafka logs: `docker-compose logs kafka`

For more detailed troubleshooting, check the application logs:

```bash
docker-compose logs quizgame
```

---

## Acknowledgments

- Built as part of a distributed systems course project
- Uses several open source libraries and frameworks
