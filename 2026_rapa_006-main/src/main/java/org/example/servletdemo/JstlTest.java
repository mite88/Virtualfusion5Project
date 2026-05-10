package org.example.servletdemo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
    urlPatterns = "/jsp/03"
)
public class JstlTest extends HttpServlet {

    // JSTL?
    // Jakarta Server Page Tag Library

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String numStr = req.getParameter("number");

        int result = numStr == null
                ? 0
                : Integer.parseInt(numStr);

        req.setAttribute("result", result);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Jstl.jsp");
        dispatcher.forward(req, resp);

    }

}
