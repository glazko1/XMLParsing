package handler;

import builder.TariffBuilder;
import entity.TariffParameter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.helpers.AttributesImpl;

import static org.testng.Assert.assertEquals;

public class TariffHandlerTest {

    private TariffHandler handler = new TariffHandler();
    private TariffHandler readyHandler = new TariffHandler();

    @BeforeClass
    public void init() {
        readyHandler.startElement("uri", "localName", "sms-price", new AttributesImpl());
        readyHandler.characters(new char[]{ '0', '.', '0', '5', '8' }, 0, 5);
        readyHandler.setTariffBuilder(new TariffBuilder("ID-10"));
    }

    @Test
    public void startElement_name_true() {
        //given
        //when
        handler.startElement("uri", "localName", "name", new AttributesImpl());
        //then
        assertEquals(handler.getCurrentParameter(), TariffParameter.NAME);
    }

    @Test
    public void characters_string_true() {
        //given
        //when
        handler.characters(new char[]{ 's', 't', 'r', 'i', 'n', 'g' }, 0, 6);
        //then
        assertEquals(handler.getData(), "string");
    }

    @Test
    public void endElement_smsPrice_true() {
        //given
        //when
        readyHandler.endElement("uri", "localName", "sms-price");
        TariffBuilder tariffBuilder = readyHandler.getTariffBuilder();
        //then
        assertEquals(tariffBuilder.getSmsPrice(), 0.058);
    }
}