package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.mockito.Mockito.*;

public class GetProductsServletTest extends AbstractServletTest {
    private final GetProductsServlet servlet = new GetProductsServlet();

    @Test
    public void getProducts() throws IOException {
        final String name1 = "product1";
        final String name2 = "product2";
        final int price1 = 13;
        final int price2 = 37;

        doNothing().when(httpServletResponse).setContentType("text/html");
        doNothing().when(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
        when(httpServletResponse.getWriter()).thenReturn(pw);

        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                stmt.executeUpdate("INSERT INTO PRODUCT (NAME, PRICE) VALUES "
                        + "(\"" + name1 + "\", " + price1 + "), "
                        + "(\"" + name2 + "\", " + price2 + ");");

                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        servlet.doGet(httpServletRequest, httpServletResponse);

        verify(pw).println("<html><body>");
        verify(pw).println(name1 + "\t" + price1 + "</br>");
        verify(pw).println(name2 + "\t" + price2 + "</br>");
        verify(pw).println("</body></html>");
    }
}
