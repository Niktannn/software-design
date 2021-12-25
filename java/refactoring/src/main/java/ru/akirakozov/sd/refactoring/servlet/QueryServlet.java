package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.handler.*;

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
        AbstractHandler handler = getHandler(command);
        if (handler != null) {
            handler.handle(response);
        } else {
            response.getWriter().println("Unknown command: " + command);
        }
    }

    private AbstractHandler getHandler(String command) {
        if ("max".equals(command)) {
            return new MaxHandler();
        } else if ("min".equals(command)) {
            return new MinHandler();
        } else if ("sum".equals(command)) {
            return new SumHandler();
        } else if ("count".equals(command)) {
            return new CountHandler();
        } else {
            return null;
        }
    }
}
