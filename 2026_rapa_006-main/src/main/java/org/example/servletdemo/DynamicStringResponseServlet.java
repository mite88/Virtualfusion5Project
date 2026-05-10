package org.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;

@WebServlet(
    urlPatterns = { "/c01/02" }
)
public class DynamicStringResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;chatset=utf-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Servlet:: DynamicStringResponseServlet</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<div>");

        IntStream.rangeClosed(1, 9)
                .forEach(i -> writer.println("<p>" + "2 x " + i + " = " + (i * 2) + "</p>"));

        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");



    }


}
