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
    urlPatterns = "/c01/10"
)
public class RequestParametersTest
        extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Query Param
        String numStr = req.getParameter("i");

        int num = Integer.parseInt(numStr);

        resp.setContentType("text/html;chatset=utf-8");

        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>" + numStr + "단 출력 :: " + "</title>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println("<h1>");
        writer.println(numStr + "단");
        writer.println("</h1>");

        writer.println("<div>");

        IntStream.rangeClosed(1, 9)
            .forEach( i -> writer.println( "<p>" + numStr + " x " + i + " = " + (i * num) + "</p>"));

        writer.println("</div>");

        writer.println("</body>");
        writer.println("</html>");





    }

}
