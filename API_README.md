# Campus Task Board API Documentation

## Base URL
http://localhost:8080/api

## Endpoints

### Get All Tasks
GET /api/tasks

### Get Task by ID
GET /api/tasks/{id}

### Create Task
POST /api/tasks
Body: { "title": "...", "description": "...", "completed": false, "priority": "HIGH" }

### Update Task
PUT /api/tasks/{id}

### Delete Task
DELETE /api/tasks/{id}

## Error Responses

### 400 Bad Request
{ "timestamp": "...", "status": 400, "error": "Bad Request", "message": "..." }

### 404 Not Found
{ "timestamp": "...", "status": 404, "error": "Not Found", "message": "..." }
