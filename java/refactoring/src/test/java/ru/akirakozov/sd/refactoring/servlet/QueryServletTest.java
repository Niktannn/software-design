package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class QueryServletTest extends AbstractServletTest {
    private final QueryServlet servlet = new QueryServlet();
    public static final String name1 = "product1";
    public static final String name2 = "product2";
    public static final int price1 = 13;
    public static final int price2 = 37;


    @Override
    @Before
    public void prepare() {
        super.prepare();

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
    }

    @Test
    public void getMin() throws IOException {
        when(httpServletRequest.getParameter("command")).thenReturn("min");
        doNothing().when(httpServletResponse).setContentType("text/html");
        doNothing().when(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
        when(httpServletResponse.getWriter()).thenReturn(pw);

        servlet.doGet(httpServletRequest, httpServletResponse);

        verify(pw).println("<html><body>");
        verify(pw).println("<h1>Product with min price: </h1>");
        verify(pw).println(name1 + "\t" + price1 + "</br>");
        verify(pw).println("</body></html>");
    }

    @Test
    public void getMax() throws IOException {
        when(httpServletRequest.getParameter("command")).thenReturn("max");
        doNothing().when(httpServletResponse).setContentType("text/html");
        doNothing().when(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
        when(httpServletResponse.getWriter()).thenReturn(pw);

        servlet.doGet(httpServletRequest, httpServletResponse);

        verify(pw).println("<html><body>");
        verify(pw).println("<h1>Product with max price: </h1>");
        verify(pw).println(name2 + "\t" + price2 + "</br>");
        verify(pw).println("</body></html>");
    }

    @Test
    public void getSum() throws IOException {
        when(httpServletRequest.getParameter("command")).thenReturn("sum");
        doNothing().when(httpServletResponse).setContentType("text/html");
        doNothing().when(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
        when(httpServletResponse.getWriter()).thenReturn(pw);

        servlet.doGet(httpServletRequest, httpServletResponse);

        verify(pw).println("<html><body>");
        verify(pw).println("Summary price: ");
        verify(pw).println(price1 + price2);
        verify(pw).println("</body></html>");
    }

    @Test
    public void getCount() throws IOException {
        when(httpServletRequest.getParameter("command")).thenReturn("count");
        doNothing().when(httpServletResponse).setContentType("text/html");
        doNothing().when(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
        when(httpServletResponse.getWriter()).thenReturn(pw);

        servlet.doGet(httpServletRequest, httpServletResponse);

        verify(pw).println("<html><body>");
        verify(pw).println("Number of products: ");
        verify(pw).println(2);
        verify(pw).println("</body></html>");
    }
}
