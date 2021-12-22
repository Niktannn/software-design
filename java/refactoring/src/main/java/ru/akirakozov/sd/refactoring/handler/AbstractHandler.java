package ru.akirakozov.sd.refactoring.handler;

import ru.akirakozov.sd.refactoring.utils.CheckedConsumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class AbstractHandler {
    public abstract void handle(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public void writeResponse(HttpServletResponse response, String query, String header,
                              CheckedConsumer<ResultSet> extractor) {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                response.getWriter().println("<html><body>");
                response.getWriter().println(header);
                while (rs.next()) {
                    extractor.accept(rs);
                }
                response.getWriter().println("</body></html>");

                rs.close();
                stmt.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
