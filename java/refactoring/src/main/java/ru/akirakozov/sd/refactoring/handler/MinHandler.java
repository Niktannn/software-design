package ru.akirakozov.sd.refactoring.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MinHandler extends AbstractReadHandler {
    public MinHandler() {
        super("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1",
                response -> rs -> {
                    String  name = rs.getString("name");
                    int price  = rs.getInt("price");
                    response.getWriter().println(name + "\t" + price + "</br>");
                });
    }

    @Override
    protected void writeInfo(PrintWriter pw) {
        pw.println("<h1>Product with min price: </h1>");
    }
}
