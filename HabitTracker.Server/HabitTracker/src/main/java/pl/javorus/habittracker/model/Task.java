package pl.javorus.habittracker.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taskId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;


    public Task(UUID taskId, User user, String title, String description, List<Tag> tags) {
        this.taskId = taskId;
        this.user = user;
        this.title = title;
        this.description = description;
        this.tags = tags;
    }

    public Task(User user, String title, String description, List<Tag> tags) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.tags = tags;
    }

    public Task() {

    }


    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}