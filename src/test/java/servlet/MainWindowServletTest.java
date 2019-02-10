package servlet;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class MainWindowServletTest extends Mockito {

    private MainWindowServlet servlet = new MainWindowServlet();

    @Test
    public void doPost_correctParameters_true() throws IOException, ServletException {
        //given
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Part xmlPart = new Part() {
            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream("src/main/resources/tariffs.xml");
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getSubmittedFileName() {
                return null;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public void write(String s) {

            }

            @Override
            public void delete() {

            }

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }
        };
        Part xsdPart = new Part() {
            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream("src/main/resources/schema.xsd");
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getSubmittedFileName() {
                return null;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public void write(String s) {

            }

            @Override
            public void delete() {

            }

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }
        };
        //when
        when(request.getPart("xml")).thenReturn(xmlPart);
        when(request.getPart("xsd")).thenReturn(xsdPart);
        when(request.getParameter("parser-type")).thenReturn("DOM");
        when(request.getRequestDispatcher("WEB-INF/table.jsp")).thenReturn(dispatcher);
        servlet.doPost(request, response);
        //then
        verify(dispatcher).forward(request, response);
    }
}