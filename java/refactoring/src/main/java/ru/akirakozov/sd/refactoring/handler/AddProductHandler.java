package ru.akirakozov.sd.refactoring.handler;

import java.io.PrintWriter;

public class AddProductHandler extends AbstractUpdateHandler {
    public AddProductHandler(String name, long price) {
        super("INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
    }

    @Override
    protected void writeInfo(PrintWriter pw) {
        pw.println("OK");
    }
}
