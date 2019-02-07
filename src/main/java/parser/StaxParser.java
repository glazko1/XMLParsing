package parser;

import builder.CallPricesBuilder;
import builder.ParametersBuilder;
import builder.TariffBuilder;
import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;
import entity.TariffingType;
import exception.ParsingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class StaxParser implements XmlParser {

    private static final StaxParser INSTANCE = new StaxParser();

    public static StaxParser getInstance() {
        return INSTANCE;
    }

    private StaxParser() {}

    private List<Tariff> tariffs;
    private TariffBuilder tariffBuilder;
    private CallPricesBuilder callPricesBuilder;
    private ParametersBuilder parametersBuilder;

    private boolean bName = false;
    private boolean bOperatorName = false;
    private boolean bPayroll = false;
    private boolean bWithinNetwork = false;
    private boolean bOtherNetworks = false;
    private boolean bLandlinePhones = false;
    private boolean bSmsPrice = false;
    private boolean bFavoriteNumbers = false;
    private boolean bTariffing = false;
    private boolean bConnectionFee = false;

    @Override
    public List<Tariff> parse(InputStream inputStream) throws ParsingException {
        tariffs = new ArrayList<>();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(inputStream);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case START_ELEMENT:
                        startElement(event);
                        break;
                    case CHARACTERS:
                        characters(event);
                        break;
                    case END_ELEMENT:
                        endElement(event);
                        break;
                }
            }
        } catch (XMLStreamException e) {
            throw new ParsingException("XML Stream error: " + e.getMessage());
        }
        return tariffs;
    }

    private void startElement(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        String qName = startElement.getName().getLocalPart();
        if ("tariff".equals(qName)) {
            Iterator<Attribute> iterator = startElement.getAttributes();
            String id = iterator.next().getValue();
            tariffBuilder = new TariffBuilder(id);
            callPricesBuilder = new CallPricesBuilder();
            parametersBuilder = new ParametersBuilder();
        } else if ("name".equals(qName)) {
            bName = true;
        } else if ("operator-name".equals(qName)) {
            bOperatorName = true;
        } else if ("payroll".equals(qName)) {
            bPayroll = true;
        } else if ("within-network".equals(qName)) {
            bWithinNetwork = true;
        } else if ("other-networks".equals(qName)) {
            bOtherNetworks = true;
        } else if ("landline-phones".equals(qName)) {
            bLandlinePhones = true;
        } else if ("sms-price".equals(qName)) {
            bSmsPrice = true;
        } else if ("favorite-numbers".equals(qName)) {
            bFavoriteNumbers = true;
        } else if ("tariffing".equals(qName)) {
            bTariffing = true;
        } else if ("connection-fee".equals(qName)) {
            bConnectionFee = true;
        }
    }

    private void characters(XMLEvent event) {
        Characters characters = event.asCharacters();
        String data = characters.getData();
        if (bName) {
            tariffBuilder.withName(data);
            bName = false;
        } else if (bOperatorName) {
            tariffBuilder.withOperatorName(data);
            bOperatorName = false;
        } else if (bPayroll) {
            tariffBuilder.withPayroll(Double.parseDouble(data));
            bPayroll = false;
        } else if (bWithinNetwork) {
            callPricesBuilder.withPriceWithinNetwork(Double.parseDouble(data));
            bWithinNetwork = false;
        } else if (bOtherNetworks) {
            callPricesBuilder.withPriceToOtherNetworks(Double.parseDouble(data));
            bOtherNetworks = false;
        } else if (bLandlinePhones) {
            callPricesBuilder.withPriceToLandlinePhones(Double.parseDouble(data));
            bLandlinePhones = false;
        } else if (bSmsPrice) {
            tariffBuilder.withSmsPrice(Double.parseDouble(data));
            bSmsPrice = false;
        } else if (bFavoriteNumbers) {
            parametersBuilder.hasFavoriteNumbers(Integer.parseInt(data));
            bFavoriteNumbers = false;
        } else if (bTariffing) {
            TariffingType tariffingType;
            if ("60-sec".equals(data)) {
                tariffingType = TariffingType.SEC_60;
            } else {
                tariffingType = TariffingType.SEC_12;
            }
            parametersBuilder.withTariffingType(tariffingType);
            bTariffing = false;
        } else if (bConnectionFee) {
            parametersBuilder.withConnectionFee(Double.parseDouble(data));
            bConnectionFee = false;
        }
    }

    private void endElement(XMLEvent event) {
        EndElement endElement = event.asEndElement();
        String name = endElement.getName().getLocalPart();
        if ("tariff".equals(name)) {
            CallPrices callPrices = callPricesBuilder.build();
            Parameters parameters = parametersBuilder.build();
            Tariff tariff = tariffBuilder.withCallPrices(callPrices)
                    .withParameters(parameters)
                    .build();
            tariffs.add(tariff);
        }
    }
}
