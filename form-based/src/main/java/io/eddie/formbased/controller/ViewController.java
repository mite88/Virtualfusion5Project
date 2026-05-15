package io.eddie.formbased.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : io.eddie.formbased.controller
 * fileName       : ViewController
 * author         : Admin
 * date           : 26. 5. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 15.        Admin       최초 생성
 */
@Controller
public class ViewController {

    @GetMapping("/login-page")
    public String loginPage(){
        return "sign_in";
    }
}
