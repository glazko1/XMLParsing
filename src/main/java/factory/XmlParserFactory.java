package factory;

import parser.DomParser;
import parser.SaxParser;
import parser.StaxParser;
import parser.XmlParser;

public class XmlParserFactory {

    private static final XmlParserFactory INSTANCE = new XmlParserFactory();

    public static XmlParserFactory getInstance() {
        return INSTANCE;
    }

    private XmlParserFactory() {}

    /**
     * Returns XML parsers in accordance with name given as parameter. Can return
     * object of any class implementing interface {@link XmlParser}.
     * @param name name of XML parser to get.
     * @return XML parser with given name.
     */
    public XmlParser getXmlParser(String name) {
        switch (name) {
            case "DOM":
                return DomParser.getInstance();
            case "SAX":
                return SaxParser.getInstance();
            case "StAX":
                return StaxParser.getInstance();
            default:
                break;
        }
        throw new RuntimeException("Name of parser is invalid!");
    }
}
