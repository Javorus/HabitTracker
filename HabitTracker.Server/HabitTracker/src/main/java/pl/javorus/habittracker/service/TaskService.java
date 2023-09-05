package pl.javorus.habittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javorus.habittracker.model.Task;
import pl.javorus.habittracker.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {

        String taskId = UUID.randomUUID().toString();
        task.setTaskId(taskId);

        return taskRepository.save(task);
    }

    public Task getTask(String taskId) {
        return taskRepository.findById(taskId).orElse(null);

    }

    public List<Task> getAllTasksForUser(String userId) {
        return taskRepository.findAllByUser_Id(userId);
    }
}