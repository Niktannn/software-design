package ru.akirakozov.sd.refactoring.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CountHandler extends AbstractHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeResponse(response,
                "SELECT COUNT(*) FROM PRODUCT",
                "Number of products: ",
                rs -> {
                    response.getWriter().println(rs.getInt(1));
                });
    }
}
