package servlet;

import entity.Tariff;
import exception.ParsingException;
import exception.ValidationException;
import factory.XmlParserFactory;
import parser.XmlParser;
import validator.XmlValidator;
import writer.ParsingResultWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/mainWindow")
@MultipartConfig
public class MainWindowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part xmlPart = request.getPart("xml");
        InputStream xmlInputStream = xmlPart.getInputStream();
        File xmlFile = new File("xmlFile.xml");
        OutputStream xmlOutputStream = new FileOutputStream(xmlFile);
        xmlOutputStream.write(xmlInputStream.readAllBytes());

        Part xsdPart = request.getPart("xsd");
        InputStream xsdInputStream = xsdPart.getInputStream();
        File xsdFile = new File("xsdFile.xsd");
        OutputStream xsdOutputStream = new FileOutputStream(xsdFile);
        xsdOutputStream.write(xsdInputStream.readAllBytes());

        XmlValidator validator = XmlValidator.getInstance();
        try {
            validator.validate(xmlFile, xsdFile);
            String parserName = request.getParameter("parser-type");
            XmlParserFactory factory = XmlParserFactory.getInstance();
            XmlParser parser = factory.getXmlParser(parserName);
            List<Tariff> tariffs = parser.parse(xmlFile);
            PrintWriter out = response.getWriter();
            ParsingResultWriter writer = ParsingResultWriter.getInstance();
            writer.writeResults(out, tariffs);
        } catch (ValidationException | ParsingException e) {
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
        }
    }
}
