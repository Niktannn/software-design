package ru.akirakozov.sd.refactoring.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MaxHandler extends AbstractHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeResponse(response,
                "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1",
                "<h1>Product with max price: </h1>",
                rs -> {
                    String  name = rs.getString("name");
                    int price  = rs.getInt("price");
                    response.getWriter().println(name + "\t" + price + "</br>");
                });
    }
}
