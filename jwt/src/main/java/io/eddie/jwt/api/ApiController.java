package io.eddie.jwt.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/member/hello")
    public String memberHello() {
        return "Hello, Member!";
    }

}
