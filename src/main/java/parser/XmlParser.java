package parser;

import entity.Tariff;
import exception.ParsingException;

import java.io.File;
import java.util.List;

public interface XmlParser {

    List<Tariff> parse(File xmlFile) throws ParsingException;
}
