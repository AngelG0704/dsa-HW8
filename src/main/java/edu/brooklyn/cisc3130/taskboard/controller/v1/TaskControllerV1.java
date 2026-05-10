package edu.brooklyn.cisc3130.taskboard.controller.v1;

import edu.brooklyn.cisc3130.taskboard.dto.TaskRequest;
import edu.brooklyn.cisc3130.taskboard.dto.TaskResponse;
import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskControllerV1 {

    private final TaskService taskService;

    public TaskControllerV1(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks().stream()
                .map(TaskResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.getCompleted() != null ? taskRequest.getCompleted() : false);
        task.setPriority(Task.Priority.valueOf(
                taskRequest.getPriority() != null ?
                        taskRequest.getPriority().toUpperCase() : "MEDIUM"));

        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TaskResponse.fromEntity(createdTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Integer id,
            @Valid @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/completed")
    public ResponseEntity<List<TaskResponse>> getCompletedTasks() {
        List<TaskResponse> tasks = taskService.getCompletedTasks()
                .stream()
                .map(TaskResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/incomplete")
    public ResponseEntity<List<TaskResponse>> getIncompleteTasks() {
        List<TaskResponse> tasks = taskService.getIncompleteTasks()
                .stream()
                .map(TaskResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> getTasksByPriority(
            @PathVariable String priority) {
        Task.Priority priorityEnum = Task.Priority.valueOf(priority.toUpperCase());
        List<TaskResponse> tasks = taskService.getTasksByPriority(priorityEnum)
                .stream()
                .map(TaskResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(@RequestParam String keyword) {
        List<TaskResponse> tasks = taskService.searchTasks(keyword)
                .stream()
                .map(TaskResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Task>> getTasksPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Task> tasks = taskService.getAllTasks(pageable);
        return ResponseEntity.ok(tasks);
    }

}