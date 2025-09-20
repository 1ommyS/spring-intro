package ru.mai.topit.volunteers.platform.intro;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mai.topit.volunteers.platform.intro.entity.User;
import ru.mai.topit.volunteers.platform.intro.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{name}")
    public User findByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        userService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
    }
}
