package io.eddie.aopdemo.controller;

import io.eddie.aopdemo.service.AopService;
import io.eddie.aopdemo.service.NonAopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : io.eddie.aopdemo.controller
 * fileName       : AopController
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
//@RestController = @Controller + @ResponseBody
@RestController
//@ResponseBody
@RequiredArgsConstructor
public class AopController {

    private final AopService aopService;
    private final NonAopService nonAopService;

    //@ResponseBody
    @GetMapping("/aop")
    public String aop(){
        //aopService.logic1();

        nonAopService.speciallLogic();
        return "OK";
    }
}
