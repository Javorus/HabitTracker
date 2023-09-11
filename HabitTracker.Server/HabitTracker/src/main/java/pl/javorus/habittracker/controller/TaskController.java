package pl.javorus.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javorus.habittracker.dto.TagRequest;
import pl.javorus.habittracker.dto.TaskRequest;
import pl.javorus.habittracker.model.Tag;
import pl.javorus.habittracker.model.Task;
import pl.javorus.habittracker.model.User;
import pl.javorus.habittracker.service.TagService;
import pl.javorus.habittracker.service.TaskService;
import pl.javorus.habittracker.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final TagService tagService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService, TagService tagService) {
        this.taskService = taskService;
        this.userService = userService;
        this.tagService = tagService;
    }
    @PostMapping("/user/{userId}")
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest, @PathVariable String userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Tag> tags = taskRequest.tagIds().stream().map(tagService::getTag).toList();
        if (tags.contains(null)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Task task = new Task(
                UUID.randomUUID().toString(),
                user,
                taskRequest.title(),
                taskRequest.description(),
                taskRequest.startDate(),
                taskRequest.duration(),
                tags
        );
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.OK);
    }
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable String taskId) {
        return taskService.getTask(taskId);
    }
    @GetMapping("/user/{userId}")
    public List<Task> getAllTasksForUser(@PathVariable String userId) {
        return taskService.getAllTasksForUser(userId);
    }

}