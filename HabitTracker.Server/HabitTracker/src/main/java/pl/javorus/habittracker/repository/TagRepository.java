package pl.javorus.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javorus.habittracker.model.Tag;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    List<Tag> findAllByUserId(String userId);
}