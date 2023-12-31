package pl.javorus.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javorus.habittracker.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findAllByUser_Id(String userId);

    List<Task> findAllByUser_IdAndTags_tagId(String userId, String tagId);
}