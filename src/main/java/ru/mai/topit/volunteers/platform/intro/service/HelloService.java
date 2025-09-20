package ru.mai.topit.volunteers.platform.intro.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HelloService {
    private final Random random = new Random();

    public int getNumber() {
        return random.nextInt(0, 100);
    }
}
