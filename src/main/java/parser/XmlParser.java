package parser;

import entity.Tariff;
import exception.ParsingException;

import java.io.InputStream;
import java.util.List;

public interface XmlParser {

    /**
     * Parses specified XML-document.
     * @param inputStream input stream of XML-document.
     * @return list of tariffs, specified by XML-document.
     * @throws ParsingException if there was an error while parsing the document.
     * @see DomParser
     * @see SaxParser
     * @see StaxParser
     */
    List<Tariff> parse(InputStream inputStream) throws ParsingException;
}
