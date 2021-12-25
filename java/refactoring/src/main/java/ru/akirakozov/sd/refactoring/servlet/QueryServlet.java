package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.handler.CountHandler;
import ru.akirakozov.sd.refactoring.handler.MaxHandler;
import ru.akirakozov.sd.refactoring.handler.MinHandler;
import ru.akirakozov.sd.refactoring.handler.SumHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            new MaxHandler().handle(request, response);
        } else if ("min".equals(command)) {
            new MinHandler().handle(request, response);
        } else if ("sum".equals(command)) {
            new SumHandler().handle(request, response);
        } else if ("count".equals(command)) {
            new CountHandler().handle(request, response);
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
