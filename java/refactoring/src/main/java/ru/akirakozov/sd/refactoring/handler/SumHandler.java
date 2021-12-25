package ru.akirakozov.sd.refactoring.handler;

import java.io.PrintWriter;

public class SumHandler extends AbstractReadHandler {
    public SumHandler() {
        super("SELECT SUM(price) FROM PRODUCT",
                response -> rs -> response.getWriter().println(rs.getInt(1)));
    }

    @Override
    protected void writeInfo(PrintWriter pw) {
        pw.println("Summary price: ");
    }
}
