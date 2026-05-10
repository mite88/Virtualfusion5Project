package io.eddie.demo.controller;

import io.eddie.demo.service.TestService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;


    @GetMapping("/sum")
    public String showModelPage(
            Numbers numbers, Model model
    ) {

        int result = testService.superComplexLogic(numbers);

        model.addAttribute("result", result);

        return "model_test_page";
    }


    @Data
    @NoArgsConstructor
    public static class Numbers {

        private int a;
        private int b;

    }

}
