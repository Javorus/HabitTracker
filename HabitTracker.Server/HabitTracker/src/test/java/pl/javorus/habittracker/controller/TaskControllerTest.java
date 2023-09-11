package pl.javorus.habittracker.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.javorus.habittracker.model.*;
import pl.javorus.habittracker.service.TagService;
import pl.javorus.habittracker.service.TaskService;
import pl.javorus.habittracker.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TaskService taskService;

    private User user;
    private Tag tag;
    private Task task;

    @BeforeEach
    public void setup() {
        String userId = UUID.randomUUID().toString();
        user = new User(userId, "username", "password");
        userService.createUser(user);

        String tagId = UUID.randomUUID().toString();
        tag = new Tag(tagId, user, "tag");
        tagService.createTag(tag);

        String taskId = UUID.randomUUID().toString();
        task = new Task(taskId, user, "title", "description", LocalDate.now(), LocalTime.now(), Arrays.asList(tag));
        taskService.createTask(task);
    }

    @Test
    public void testCreateTaskWhenValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/user/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"title\",\"description\":\"description\",\"startDate\":\"2022-01-01\",\"duration\":\"10:00:00\",\"tagIds\":[\"" + tag.getTagId() + "\"]}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.taskId").value(task.getTaskId()));
    }

    @Test
    public void testCreateTaskWhenNonExistentUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/user/nonexistentuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"title\",\"description\":\"description\",\"startDate\":\"2022-01-01\",\"duration\":\"10:00:00\",\"tagIds\":[\"" + tag.getTagId() + "\"]}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateTaskWhenNonExistentTags() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/user/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"title\",\"description\":\"description\",\"startDate\":\"2022-01-01\",\"duration\":\"10:00:00\",\"tagIds\":[\"nonexistenttag\"]}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}