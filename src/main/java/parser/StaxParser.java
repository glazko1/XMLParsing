package parser;

import builder.CallPricesBuilder;
import builder.ParametersBuilder;
import builder.TariffBuilder;
import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;
import entity.TariffParameter;
import entity.TariffingType;
import exception.ParsingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static entity.TariffParameter.CONNECTION_FEE;
import static entity.TariffParameter.FAVORITE_NUMBERS;
import static entity.TariffParameter.FREE;
import static entity.TariffParameter.LANDLINE_PHONES;
import static entity.TariffParameter.LAUNCH_DATE;
import static entity.TariffParameter.NAME;
import static entity.TariffParameter.OPERATOR_NAME;
import static entity.TariffParameter.OTHER_NETWORKS;
import static entity.TariffParameter.PAYROLL;
import static entity.TariffParameter.SMS_PRICE;
import static entity.TariffParameter.TARIFFING;
import static entity.TariffParameter.WITHIN_NETWORK;
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

    private TariffParameter currentParameter = FREE;

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

    /**
     * Matches beginning of element. Sets the type of this element into {@code
     * currentParameter}.
     * @param event XML-event found by event reader.
     * @see TariffParameter
     */
    private void startElement(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        String qName = startElement.getName().getLocalPart();
        switch (qName) {
            case "tariff":
                Iterator<Attribute> iterator = startElement.getAttributes();
                String id = iterator.next().getValue();
                tariffBuilder = new TariffBuilder(id);
                callPricesBuilder = new CallPricesBuilder();
                parametersBuilder = new ParametersBuilder();
                break;
            case "name":
                currentParameter = NAME;
                break;
            case "operator-name":
                currentParameter = OPERATOR_NAME;
                break;
            case "payroll":
                currentParameter = PAYROLL;
                break;
            case "within-network":
                currentParameter = WITHIN_NETWORK;
                break;
            case "other-networks":
                currentParameter = OTHER_NETWORKS;
                break;
            case "landline-phones":
                currentParameter = LANDLINE_PHONES;
                break;
            case "sms-price":
                currentParameter = SMS_PRICE;
                break;
            case "favorite-numbers":
                currentParameter = FAVORITE_NUMBERS;
                break;
            case "tariffing":
                currentParameter = TARIFFING;
                break;
            case "connection-fee":
                currentParameter = CONNECTION_FEE;
                break;
            case "launch-date":
                currentParameter = LAUNCH_DATE;
                break;
            default:
                break;
        }
    }

    /**
     * Gets information about element in {@code currentParameter}. Writes it into according
     * builder. Sets {@code FREE} into current parameter when information was read.
     * @param event XML-event found by event reader.
     * @see TariffParameter
     */
    private void characters(XMLEvent event) {
        Characters characters = event.asCharacters();
        String data = characters.getData();
        switch (currentParameter) {
            case NAME:
                tariffBuilder.withName(data);
                break;
            case OPERATOR_NAME:
                tariffBuilder.withOperatorName(data);
                break;
            case PAYROLL:
                tariffBuilder.withPayroll(Double.parseDouble(data));
                break;
            case WITHIN_NETWORK:
                callPricesBuilder.withPriceWithinNetwork(Double.parseDouble(data));
                break;
            case OTHER_NETWORKS:
                callPricesBuilder.withPriceToOtherNetworks(Double.parseDouble(data));
                break;
            case LANDLINE_PHONES:
                callPricesBuilder.withPriceToLandlinePhones(Double.parseDouble(data));
                break;
            case SMS_PRICE:
                tariffBuilder.withSmsPrice(Double.parseDouble(data));
                break;
            case FAVORITE_NUMBERS:
                parametersBuilder.hasFavoriteNumbers(Integer.parseInt(data));
                break;
            case TARIFFING:
                TariffingType tariffingType;
                if ("60-sec".equals(data)) {
                    tariffingType = TariffingType.SEC_60;
                } else {
                    tariffingType = TariffingType.SEC_12;
                }
                parametersBuilder.withTariffingType(tariffingType);
                break;
            case CONNECTION_FEE:
                parametersBuilder.withConnectionFee(Double.parseDouble(data));
                break;
            case LAUNCH_DATE:
                parametersBuilder.withLaunchDate(data);
                break;
            default:
                break;
        }
        currentParameter = FREE;
    }

    /**
     * Matches end of head element (tariff). Builds call prices, parameters and tariff.
     * Adds created tariff into tariff list.
     * @param event XML-event found by event reader.
     */
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
