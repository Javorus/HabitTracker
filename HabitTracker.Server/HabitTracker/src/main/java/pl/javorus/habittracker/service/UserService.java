package pl.javorus.habittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javorus.habittracker.dto.SignRequest;
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

    public User createUser(SignRequest signRequest) {

        return userRepository.save(new User( UUID.randomUUID(), signRequest.getUsername(), signRequest.getPassword()));
    }

    public User getUser(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByUsername(String username){return userRepository.findByUsername(username); }


}