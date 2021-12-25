package ru.akirakozov.sd.refactoring.handler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractUpdateHandler extends AbstractHandler {
    public AbstractUpdateHandler(String query) {
        super(query);
    }

    @Override
    protected void executeCommand(Statement stmt,
                                  HttpServletResponse response) throws SQLException, IOException {
        stmt.executeUpdate(query);
        writeInfo(response.getWriter());
    }

    protected abstract void writeInfo(PrintWriter pw);
}
