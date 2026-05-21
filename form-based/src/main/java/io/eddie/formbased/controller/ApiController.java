package io.eddie.formbased.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : io.eddie.formbased.controller
 * fileName       : ApiController
 * author         : Admin
 * date           : 26. 5. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */
@RestController
public class ApiController {

    @GetMapping("/user")
    public String user(){
        return "User";
    }

    @GetMapping("/manger")
    public String manger(){
        return "Manger";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }
}
