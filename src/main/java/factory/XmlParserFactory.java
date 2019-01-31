package factory;

import parser.DomParser;
import parser.SaxParser;
import parser.XmlParser;

public class XmlParserFactory {

    private static final XmlParserFactory INSTANCE = new XmlParserFactory();

    public static XmlParserFactory getInstance() {
        return INSTANCE;
    }

    private XmlParserFactory() {}

    public XmlParser getXmlParser(String name) {
        switch (name) {
            case "DOM":
                return DomParser.getInstance();
            case "SAX":
                return SaxParser.getInstance();
            case "StAX":
                break;
            default:
                break;
        }
        throw new RuntimeException("Name of parser is invalid!");
    }
}
