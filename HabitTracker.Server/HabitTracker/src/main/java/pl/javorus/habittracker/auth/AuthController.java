package pl.javorus.habittracker.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javorus.habittracker.dto.SignRequest;
import pl.javorus.habittracker.model.User;
import pl.javorus.habittracker.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(existingUser.getUserId().toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignRequest signRequest) {
        User existingUser = userService.getUserByUsername(signRequest.getUsername());

        if (existingUser == null) {

            User createdUser = userService.createUser(signRequest);

            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User already exists");
        }
    }
}