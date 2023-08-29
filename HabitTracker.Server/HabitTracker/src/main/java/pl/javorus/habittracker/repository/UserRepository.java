package pl.javorus.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javorus.habittracker.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}