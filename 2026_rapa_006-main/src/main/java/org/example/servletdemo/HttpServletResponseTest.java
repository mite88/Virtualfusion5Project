package org.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    urlPatterns = { "/c01/05" }
)
public class HttpServletResponseTest
        extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // header
        resp.setHeader("X-CUSTOM-HEADER","CUSTOM_VALUE");
        resp.setHeader("X-SUPER-COMPLEX-KEY", "SUPER_COMPLEX_VALUE");

        Cookie cookie = new Cookie("custom-cookie", "custom-value");

        cookie.setMaxAge(3600);
        cookie.setPath("/");

        resp.addCookie(cookie);

        // Response Body
        PrintWriter writer = resp.getWriter();

        writer.write("{ \"message\" : \"Hello World!\" }");
        writer.flush();


    }

}
