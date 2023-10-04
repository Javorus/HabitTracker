package pl.javorus.habittracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
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
    public Task createTask(@RequestBody TaskRequest taskRequest, @PathVariable String userId) {
        User user = userService.getUser(userId);
        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                return null;
        }

        List<Tag> tags = new ArrayList<>();
        if (taskRequest.tagIds() != null) {
            logger.info("Retrieved tagIds: {}", taskRequest.tagIds());
            tags = taskRequest.tagIds().stream()
                    .map(tagService::getTag)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        if (tags.size() < taskRequest.tagIds().size()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return null;
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
        return taskService.createTask(task);
    }
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable String taskId) {
        return taskService.getTask(taskId);
    }
    @GetMapping("/user/{userId}/tag/{tagId}")
    public List<Task> getUserTasksWithTag(@RequestParam String userId, @RequestParam String tagId) {
        return taskService.getAllTasksForUserWithTag(userId, tagId);
    }
    @GetMapping("/user/{userId}")
    public List<Task> getAllTasksForUser(@PathVariable String userId) {
        return taskService.getAllTasksForUser(userId);
    }

}