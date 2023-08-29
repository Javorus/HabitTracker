package pl.javorus.habittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javorus.habittracker.model.User;
import pl.javorus.habittracker.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {

        UUID userId = UUID.randomUUID();
        user.setUserId(userId);

        return userRepository.save(user);
    }

    public User getUser(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }


}