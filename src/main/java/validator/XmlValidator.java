package validator;

import exception.ValidationException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private static final XmlValidator INSTANCE = new XmlValidator();

    public static XmlValidator getInstance() {
        return INSTANCE;
    }

    private XmlValidator() {}

    public boolean validate(File xmlFile, File xsdFile) throws ValidationException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            Schema schema = factory.newSchema(xsdFile);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            parser.parse(xmlFile, new DefaultHandler());
            return true;
        } catch (ParserConfigurationException e) {
            throw new ValidationException("XmlParser configuration error: " + e.getMessage());
        } catch (SAXException e) {
            throw new ValidationException("SAX error: " + e.getMessage());
        } catch (IOException e) {
            throw new ValidationException("I/O error: " + e.getMessage());
        }
    }
}
