package org.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;


@WebServlet(
        urlPatterns = { "/c01/03" }
)
public class HttpServletRequestHandlerTest
        extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String method = req.getMethod();
        String requestURI = req.getRequestURI();

        System.out.println("method = " + method);
        System.out.println("requestURI = " + requestURI);

        // Spring Security ...
        // HTTP Request 인증정보
        String authType = req.getAuthType();
        System.out.println("authType = " + authType);

        // Headers
        // HTTP Request
        Collections.list(req.getHeaderNames())
                .forEach(
                    headerName -> System.out.println(headerName + " = " + req.getHeader(headerName))
                );

        // Query Parameter
        // Collection API
        Map<String, String[]> paramMap = req.getParameterMap();

        paramMap.forEach(
                (key, value) -> System.out.println("key = " + key + " value = " + Arrays.toString(value))
        );


    }

}
