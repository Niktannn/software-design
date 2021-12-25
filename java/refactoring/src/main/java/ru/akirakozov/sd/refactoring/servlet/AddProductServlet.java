package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.handler.AddProductHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        new AddProductHandler(request.getParameter("name"),
                Long.parseLong(request.getParameter("price")))
                .handle(response);
    }
}
