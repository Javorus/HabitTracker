package pl.javorus.habittracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.javorus.habittracker.model.Task;
import pl.javorus.habittracker.repository.TaskRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTaskWhenTaskIsGivenThenUUIDIsAssignedAndTaskIsSaved() {
        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.createTask(task);

        assertNotNull(savedTask.getTaskId());
        verify(taskRepository).save(task);
    }

    @Test
    public void testCreateTaskWhenTaskIsGivenThenSaveIsCalled() {
        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);

        taskService.createTask(task);

        verify(taskRepository).save(task);
    }
}