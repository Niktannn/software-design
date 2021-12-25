package ru.akirakozov.sd.refactoring.handler;

import java.io.PrintWriter;

public class CountHandler extends AbstractReadHandler {
    public CountHandler() {
        super("SELECT COUNT(*) FROM PRODUCT",
                response -> rs -> response.getWriter().println(rs.getInt(1)));
    }

    @Override
    protected void writeInfo(PrintWriter pw) {
        pw.println("Number of products: ");
    }
}
