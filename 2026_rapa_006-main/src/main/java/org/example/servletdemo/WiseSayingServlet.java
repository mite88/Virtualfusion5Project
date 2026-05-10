package org.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "WiseSayingServlet",
        urlPatterns = { "/wise" }
)
public class WiseSayingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Http Response Content-Type
        resp.setContentType("text/html;chatset=utf-8");

        resp.getWriter().println("오늘의 격언:");
        resp.getWriter().println("인내는 쓰다 그러나 그 열매는 달다");

    }

}
