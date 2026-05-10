package io.eddie.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HelloController {

    @RequestMapping(method = {RequestMethod.GET}, path = "/hello-world")
    public void receiveRequestFromBrowser() {
        System.out.println("Hello World!");
        System.out.println("요청을 받아서 이 메세지를 출력합니다!");
    }

    @GetMapping(path = "/print-string-1")
    public void printString1(HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();

        writer.println("Hello World!");
        writer.flush();

    }

    @GetMapping(path = "/print-string-2")
    public void printString2(HttpServletResponse resp) throws IOException {

        resp.setHeader("Content-Type", "text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.println("Hello World!");
        writer.println("안녕하세요! 한글이 잘 보이시죠?");
        writer.flush();

    }

    @ResponseBody
    @GetMapping("/print-string-3")
    public String printString3() {
        return "안녕하세요!";
    }

    @ResponseBody
    @GetMapping("/print-integer")
    public Integer printInteger() {
        return 1;
    }

    @ResponseBody
    @GetMapping("/print-boolean")
    public Boolean printBoolean() {
        return true;
    }

    @ResponseBody
    @GetMapping("/print-data-structure")
    public String[] printDataStructure() {
        return new String[] {"Hello, ", "World!"};
    }

    @ResponseBody
    @GetMapping("/print-obj")
    public SomeType printObject() {
        return new SomeType();
    }


    @Getter
    class SomeType {
        private final String data = "데이터";
    }

    @ResponseBody
    @GetMapping("/print-string-html")
    public String printStringHtml() {
        return """
        <html>
        <head>
            <title>Hello, World!</title>
        </head>
        <body>
            <h1> Hello, World! </h1>
        </body>
        </html>
        """;
    }


}
