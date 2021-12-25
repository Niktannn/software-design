package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.handler.AddProductHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new AddProductHandler(request.getParameter("name"),
                Long.parseLong(request.getParameter("price")))
                .handle(request, response);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
