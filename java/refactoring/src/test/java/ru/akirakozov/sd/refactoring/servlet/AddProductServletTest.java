package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Test;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Assert;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class AddProductServletTest extends AbstractServletTest {
    private final AddProductServlet servlet = new AddProductServlet();

    @Test
    public void addProduct() throws IOException {
        final String name = "product";
        final String price = "12345";

        when(httpServletRequest.getParameter("name")).thenReturn(name);
        when(httpServletRequest.getParameter("price")).thenReturn(price);
        doNothing().when(httpServletResponse).setContentType("text/html");
        doNothing().when(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
        when(httpServletResponse.getWriter()).thenReturn(pw);
        doNothing().when(pw).println("OK");

        servlet.doGet(httpServletRequest, httpServletResponse);


        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT"
                        + " WHERE NAME = \"" + name + "\""
                        + " AND PRICE = " + price
                        + ";");

                Assert.assertTrue(rs.next());
                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
