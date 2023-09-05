package pl.javorus.habittracker.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class TaskRequest {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalTime duration;
    private List<String> tagIds;

    public TaskRequest(String title, String description, LocalDate startDate, LocalTime duration, List<String> tagIds) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.duration = duration;
        this.tagIds = tagIds;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }
}
     