package org.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    urlPatterns = { "/c01/01" }
)
public class HtmlStringServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;chatset=utf-8");

        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <title>Servlet:: HelloServlet</title>" +
                "</head>" +
                "<body>" +
                "    <h1>Hello Servlet!</h1>" +
                "    <p>ㅋㅋ 재밌다</p>" +
                "</body>" +
                "</html>");

    }

}
