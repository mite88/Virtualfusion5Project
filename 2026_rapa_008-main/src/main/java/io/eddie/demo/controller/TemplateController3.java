package io.eddie.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class TemplateController3 {

    @GetMapping("/page-3")
    public String showPage3(
            Model model
    ) {
        model.addAttribute("msg", "Hello, World!");
        return "model_test";
    }

    @GetMapping("/path1/{name}") // a aa test
    public String showIndex1(
            @PathVariable(name = "name") String name
    ) {

        System.out.println("name = " + name);

        return "index";
    }

    // ?
    @GetMapping("/path1/test")
    public String showIndex2() {

        System.out.println("!!");

        return "index";
    }

    @GetMapping(value = {"/path2", "/path2/{name}" })
    public String showIndex3(
            @PathVariable(name = "name") Optional<String> name
    ) {
        System.out.println("!!");
        return "index";
    }

    @GetMapping("/path3/{name}")
    public String showIndex3(
            @PathVariable String name
    ) {

        System.out.println("name = " + name);

        return "index";
    }

    @GetMapping("/boards/{boardName}/post/{postId}")
    public String showPost(
            @PathVariable String boardName,
            @PathVariable Integer postId
    ) {

        System.out.println("boardName = " + boardName);
        System.out.println("postId = " + postId);

        return "index";
    }

}
