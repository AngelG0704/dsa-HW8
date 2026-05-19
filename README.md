Project Description - 

This project was essentially a backend for a task manager API, this would be the part that processes information that a user would
enter if this was a fully made app with a frontend. I am able to to create tasks that contain a title, description, priority
and a completion status. I was able to then use actions that would create, read, update, and delete tasks. Version 4 adds 
Spring Security, CORS configuration, API versioning, integration tests, Swagger UI documentation, and custom validation.

How to Run -

1. Prerequisites 

Java 17 or 21
Maven

2. Clone Repository 

git clone https://github.com/AngelG0704/Homework-8.git

cd campus-taskboard

3. Run the application

mvnw.cmd spring-boot:run

4. Confirm it's running

look for Started CampusTaskboardApplication in the console and then the server should be avalible at http://localhost:8080/api/tasks

API endpoints- 

Method            Endpoint         Description
GET               /api/tasks       Returns all tasks
GET               /api/tasks/{id}  Returns a single tasks by ID
POST              /api/tasks       Creates a new task
PUT              /api/tasks/{id}   Updates an existing task
DELETE          /api/tasks/{id}    Deletes a task

New endpoints

Method      Endpoint                            Description
GET         /api/tasks/completed                Returns completed tasks
GET         /api/tasks/incomplete               Returns incomplete tasks
GET         /api/tasks/priority/{priority}      Filters by priority
GET         /api/tasks/search?keyword=...       Search by keyword
GET         /api/tasks/paginated                Returns paginated results
DELETE      /api/tasks/{id}                     Soft deletes a task
PATCH       /api/tasks/{id}/restore             Restores a soft deleted task
GET         /actuator/health                    Returns application health status
GET         /api/v1/tasks                       Returns all tasks (versioned)
GET         /swagger-ui.html                    Interactive API documentation

Error Response Format 

{
  "timestamp": "2026-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Task with ID 999 not found",
  "path": "/api/tasks/999"
}

DTOs Section

Requests use TaskRequest: only title, description, completed, priority

Responses return TaskResponse: includes id, timestamps, excludes internal fields

Note that all /api/tasks/** endpoints are public and no authentication is required for this version

API Documentation
Interactive documentation available at:
http://localhost:8080/swagger-ui.html

Video Submission-

https://youtu.be/s6PNC_MxvII
