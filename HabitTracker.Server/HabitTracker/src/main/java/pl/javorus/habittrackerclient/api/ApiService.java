package pl.javorus.habittrackerclient.api;

import pl.javorus.habittrackerclient.model.Task;
import pl.javorus.habittrackerclient.model.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiService {
    private static List<Task> tasks = new ArrayList<>();
    private static List<Tag> tags = new ArrayList<>();
    private static Map<Task, List<Tag>> taskTagsMap = new HashMap<>();

    public static List<Task> fetchTasks() {
        return tasks;
    }

    public static void createTask(Task task) {
        tasks.add(task);
    }

    public static void deleteTask(Task task) {
        tasks.remove(task);
        taskTagsMap.remove(task);
    }

    public static void addTagToTask(Task task, Tag tag) {
        List<Tag> taskTags = taskTagsMap.getOrDefault(task, new ArrayList<>());
        taskTags.add(tag);
        taskTagsMap.put(task, taskTags);
    }

    public static void removeTagFromTask(Task task, Tag tag) {
        List<Tag> taskTags = taskTagsMap.getOrDefault(task, new ArrayList<>());
        taskTags.remove(tag);
        taskTagsMap.put(task, taskTags);
    }

    public static List<Tag> getTagsForTask(Task task) {
        return taskTagsMap.getOrDefault(task, new ArrayList<>());
    }

    public static List<Task> getTasksForTag(Tag tag) {
        List<Task> tasksForTag = new ArrayList<>();
        for (Map.Entry<Task, List<Tag>> entry : taskTagsMap.entrySet()) {
            if (entry.getValue().contains(tag)) {
                tasksForTag.add(entry.getKey());
            }
        }
        return tasksForTag;
    }

    public static void createTag(Tag tag) {
        tags.add(tag);
    }

    public static void deleteTag(Tag tag) {
        tags.remove(tag);
        for (List<Tag> taskTags : taskTagsMap.values()) {
            taskTags.remove(tag);
        }
    }
}