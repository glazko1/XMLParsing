package factory;

import org.testng.annotations.Test;
import parser.DomParser;
import parser.SaxParser;
import parser.StaxParser;
import parser.XmlParser;

import static org.testng.Assert.assertEquals;

public class XmlParserFactoryTest {

    private XmlParserFactory factory = XmlParserFactory.getInstance();

    @Test
    public void getXmlParser_DOM_true() {
        //given
        //when
        XmlParser parser = factory.getXmlParser("DOM");
        //then
        assertEquals(parser, DomParser.getInstance());
    }

    @Test
    public void getXmlParser_SAX_true() {
        //given
        //when
        XmlParser parser = factory.getXmlParser("SAX");
        //then
        assertEquals(parser, SaxParser.getInstance());
    }

    @Test
    public void getXmlParser_StAX_true() {
        //given
        //when
        XmlParser parser = factory.getXmlParser("StAX");
        //then
        assertEquals(parser, StaxParser.getInstance());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void getXmlParser_incorrectParameter_true() {
        //given
        //when
        XmlParser parser = factory.getXmlParser("parser");
        //then
        //RuntimeException expected
    }
}