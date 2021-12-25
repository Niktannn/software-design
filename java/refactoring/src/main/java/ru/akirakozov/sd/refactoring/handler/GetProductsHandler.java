package ru.akirakozov.sd.refactoring.handler;

import java.io.PrintWriter;

public class GetProductsHandler extends AbstractReadHandler {
    public GetProductsHandler() {
        super("SELECT * FROM PRODUCT",
                response -> rs -> {
                    String  name = rs.getString("name");
                    int price  = rs.getInt("price");
                    response.getWriter().println(name + "\t" + price + "</br>");
                });
    }

    @Override
    protected void writeInfo(PrintWriter pw) {

    }
}
