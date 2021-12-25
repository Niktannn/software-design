package ru.akirakozov.sd.refactoring.handler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractHandler {
    protected final String query;

    public AbstractHandler(String query) {
        this.query = query;
    }

    public void handle(HttpServletResponse response) {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                executeCommand(stmt, response);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected abstract void executeCommand(Statement stmt,
                                           HttpServletResponse response) throws SQLException, IOException;
}
