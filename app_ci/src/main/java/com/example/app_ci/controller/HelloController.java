package com.example.app_ci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.example.app_ci.controller
 * fileName       : HelloController
 * author         : Admin
 * date           : 26. 6. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 6. 12.        Admin       최초 생성
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }


}
