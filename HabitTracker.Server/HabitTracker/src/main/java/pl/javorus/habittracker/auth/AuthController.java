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
    public ResponseEntity<String> signIn(@RequestBody SignRequest signRequest) {
        User existingUser = userService.getUserByUsername(signRequest.username());

        if (existingUser != null && existingUser.getPassword().equals(signRequest.password())) {
            return ResponseEntity.ok(existingUser.getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignRequest signRequest) {
        User existingUser = userService.getUserByUsername(signRequest.username());

        if (existingUser == null) {
            User createdUser = userService.createUser(
                    new User(
                            UUID.randomUUID().toString(),
                            signRequest.username(),
                            signRequest.password()
                    ));


            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User already exists");
        }
    }
}