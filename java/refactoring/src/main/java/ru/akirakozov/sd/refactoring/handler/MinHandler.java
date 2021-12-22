package ru.akirakozov.sd.refactoring.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MinHandler extends AbstractHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeResponse(response,
                "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1",
                "<h1>Product with min price: </h1>",
                rs -> {
                    String  name = rs.getString("name");
                    int price  = rs.getInt("price");
                    response.getWriter().println(name + "\t" + price + "</br>");
                });
    }
}
