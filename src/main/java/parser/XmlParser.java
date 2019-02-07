package parser;

import entity.Tariff;
import exception.ParsingException;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface XmlParser {

    List<Tariff> parse(InputStream inputStream) throws ParsingException;
}
