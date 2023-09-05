package pl.javorus.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javorus.habittracker.model.Task;
import pl.javorus.habittracker.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable String taskId) {
        return taskService.getTask(taskId);
    }
    @GetMapping("/{userId}")
    public List<Task> getAllTasksForUser(@PathVariable String userId) {
        return taskService.getAllTasksForUser(userId);
    }

}