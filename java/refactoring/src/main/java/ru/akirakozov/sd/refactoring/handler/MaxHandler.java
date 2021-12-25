package ru.akirakozov.sd.refactoring.handler;

import java.io.PrintWriter;

public class MaxHandler extends AbstractReadHandler {
    public MaxHandler() {
        super("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1",
                response -> rs -> {
                    String  name = rs.getString("name");
                    int price  = rs.getInt("price");
                    response.getWriter().println(name + "\t" + price + "</br>");
                });
    }

    @Override
    protected void writeInfo(PrintWriter pw) {
        pw.println("<h1>Product with max price: </h1>");
    }
}
