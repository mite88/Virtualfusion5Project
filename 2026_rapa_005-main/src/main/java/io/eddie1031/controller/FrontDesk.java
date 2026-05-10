package io.eddie1031.controller;

import io.eddie1031.app.Chief;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.util.List;

//@Component
@Controller
public class FrontDesk {

    private Chief chief;

    public FrontDesk(Chief chief) {
        this.chief = chief;
    }

    @GetMapping("/")
    public String orderCuisine(
            @RequestParam(name = "ingredients", required = false) List<String> ingredients
    ) {

        if ( ingredients != null ) {
            chief.cook(ingredients);
        }

        // /WEB-INF/hall.html
        return "hall";
    }

    @GetMapping("/check-exp")
    public void checkExp(
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        PrintWriter writer = response.getWriter();

        writer.println("current exp : " + chief.checkExp());
    }

}
