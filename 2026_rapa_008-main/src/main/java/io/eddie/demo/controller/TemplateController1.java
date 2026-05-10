package io.eddie.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController1 {

    // resources/templates/ooooo.html
    @GetMapping("/page-1")
    public String showPage1() {
        return "hello_page";
    }



}
