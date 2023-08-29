package pl.javorus.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javorus.habittracker.model.Tag;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    // Add custom methods if needed
}