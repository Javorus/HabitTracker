package pl.javorus.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javorus.habittracker.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTagsIn(List<String> tags);

}