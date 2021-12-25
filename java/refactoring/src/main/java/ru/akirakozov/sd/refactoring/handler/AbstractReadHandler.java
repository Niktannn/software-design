package ru.akirakozov.sd.refactoring.handler;

import ru.akirakozov.sd.refactoring.utils.CheckedConsumer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public abstract class AbstractReadHandler extends AbstractHandler {
    final Function<HttpServletResponse, CheckedConsumer<ResultSet>> extractor;

    public AbstractReadHandler(String query, Function<HttpServletResponse, CheckedConsumer<ResultSet>> extractor) {
        super(query);
        this.extractor = extractor;
    }

    @Override
    protected void executeCommand(Statement stmt,
                                  HttpServletResponse response) throws SQLException, IOException {
        ResultSet rs = stmt.executeQuery(query);
        response.getWriter().println("<html><body>");
        writeInfo(response.getWriter());
        while (rs.next()) {
            extractor.apply(response).accept(rs);
        }
        response.getWriter().println("</body></html>");

        rs.close();
    }

    protected abstract void writeInfo(PrintWriter pw);
}
