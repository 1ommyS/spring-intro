package ru.mai.topit.volunteers.platform.intro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mai.topit.volunteers.platform.intro.entity.User;
import ru.mai.topit.volunteers.platform.intro.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // crud - create, read, update, delete

    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(
                () -> {
                    log.error("User with name {} not found", name);
                    return new RuntimeException("User not found");
                }
        );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        var userFromDb = userRepository.findById(user.getId()).orElseThrow(() -> {
            log.error("User with id {} not found", user.getId());
            return new RuntimeException("User not found");
        });

        if (!userFromDb.getAge().equals(user.getAge())) {
            userFromDb.setAge(user.getAge());
        }

        if (!userFromDb.getEmail().equals(user.getEmail())) {
            userFromDb.setEmail(user.getEmail());
        }

        return userRepository.save(userFromDb);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
