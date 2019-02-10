package servlet;

import entity.Tariff;
import exception.ParsingException;
import exception.ValidationException;
import factory.XmlParserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.XmlParser;
import validator.XmlValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/mainWindow")
@MultipartConfig
public class MainWindowServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(MainWindowServlet.class);

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
        Part xsdPart = request.getPart("xsd");
        try (InputStream xmlInputStream = xmlPart.getInputStream();
             InputStream parsingInputStream = xmlPart.getInputStream();
             InputStream xsdInputStream = xsdPart.getInputStream()) {
            XmlValidator validator = XmlValidator.getInstance();
            validator.validate(xmlInputStream, xsdInputStream);
            String parserName = request.getParameter("parser-type");
            XmlParserFactory factory = XmlParserFactory.getInstance();
            XmlParser parser = factory.getXmlParser(parserName);
            List<Tariff> tariffs = parser.parse(parsingInputStream);
            request.setAttribute("tariffs", tariffs);
            String tableLanguage = request.getParameter("table-language");
            String locale = "RUS".equals(tableLanguage) ? "text_ru_RU" : "text_en_EN";
            request.setAttribute("locale", locale);
            request.getRequestDispatcher("WEB-INF/table.jsp").forward(request, response);
        } catch (ValidationException | ParsingException e) {
            LOGGER.error(e.getMessage());
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
        }
    }
}
