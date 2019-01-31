package parser;

import entity.Tariff;
import exception.ParsingException;
import handler.TariffHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxParser implements XmlParser {

    private static final SaxParser INSTANCE = new SaxParser();

    public static SaxParser getInstance() {
        return INSTANCE;
    }

    private SaxParser() {}

    @Override
    public List<Tariff> parse(File xmlFile) throws ParsingException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            TariffHandler handler = new TariffHandler();
            parser.parse(xmlFile, handler);
            return handler.getTariffs();
        } catch (ParserConfigurationException e) {
            throw new ParsingException("Parser configuration error: " + e.getMessage());
        } catch (SAXException e) {
            throw new ParsingException("SAX error: " + e.getMessage());
        } catch (IOException e) {
            throw new ParsingException("I/O error: " + e.getMessage());
        }
    }
}
