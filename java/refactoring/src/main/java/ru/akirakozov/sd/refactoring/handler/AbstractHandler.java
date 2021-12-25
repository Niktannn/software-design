package ru.akirakozov.sd.refactoring.handler;

import javax.servlet.http.HttpServletRequest;
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

    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                executeCommand(stmt, request, response);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void executeCommand(Statement stmt, HttpServletRequest request,
                                           HttpServletResponse response) throws SQLException, IOException;
}
