package org.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(
    urlPatterns = { "/c01/04" }
)
public class ReceiveUserParameterTest
        extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String favorite = req.getParameter("favorite");

        System.out.println("favorite = " + favorite);

        String[] favorites = req.getParameterValues("favorite");
        System.out.println("favorites...");
        Arrays.stream(favorites)
                .forEach(System.out::println);

    }

}
