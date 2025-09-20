package ru.mai.topit.volunteers.platform.intro;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mai.topit.volunteers.platform.intro.dto.GetUserDto;
import ru.mai.topit.volunteers.platform.intro.service.HelloService;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
@Slf4j
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @GetMapping()
    public int sayHello() {
        return helloService.getNumber();
    }

    @PostMapping("/to-name")
    public String sayHelloPost(@RequestBody String name) {
        return "Hello " + name;
    }

    @PostMapping("/to-name/{name}")
    public String sayHelloPostTo(@PathVariable("name") String name) {
        return "Hello " + name;
    }

    @GetMapping("/params")
    public Map<String, String> selectByParams(@RequestParam Map<String, String> params) {
        return params;
    }

    @PostMapping
    public ResponseEntity getRequestBodyInJson(@RequestBody GetUserDto dto) {
        log.info("Get request body: {}", dto);

        return ResponseEntity.status(400).body(dto);
    }
}
