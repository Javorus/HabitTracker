package pl.javorus.habittracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    private String taskId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalTime duration;

    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;


    public Task(String taskId, User user, String title, String description, LocalDate startDate, LocalTime duration, List<Tag> tags) {
        this.taskId = taskId;
        this.user = user;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.duration = duration;
        this.tags = tags;
    }
}