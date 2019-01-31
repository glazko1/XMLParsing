package parser;

import builder.CallPricesBuilder;
import builder.ParametersBuilder;
import builder.TariffBuilder;
import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;
import entity.TariffingType;
import exception.ParsingException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements XmlParser {

    private static final DomParser INSTANCE = new DomParser();

    public static DomParser getInstance() {
        return INSTANCE;
    }

    private DomParser() {}

    @Override
    public List<Tariff> parse(File xmlFile) throws ParsingException {
        List<Tariff> tariffs = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList nodeList = document.getElementsByTagName("tariff");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String name = getElement(element, "name");
                String operatorName = getElement(element, "operator-name");
                double payroll = Double.parseDouble(getElement(element, "payroll"));
                double withinNetwork = Double.parseDouble(getElement(element, "within-network"));
                double otherNetworks = Double.parseDouble(getElement(element,"other-networks"));
                double landlinePhones = Double.parseDouble(getElement(element,"landline-phones"));
                double smsPrice = Double.parseDouble(getElement(element,"sms-price"));
                int favoriteNumbers = Integer.parseInt(getElement(element, "favorite-numbers"));
                String tariffing = getElement(element, "tariffing");
                TariffingType tariffingType = "60-sec".equals(tariffing) ? TariffingType.SEC_60 : TariffingType.SEC_12;
                double connectionFee = Double.parseDouble(getElement(element, "connection-fee"));
                CallPricesBuilder callPricesBuilder = new CallPricesBuilder();
                CallPrices callPrices = callPricesBuilder.withPriceWithinNetwork(withinNetwork)
                        .withPriceToOtherNetworks(otherNetworks)
                        .withPriceToLandlinePhones(landlinePhones)
                        .build();
                ParametersBuilder parametersBuilder = new ParametersBuilder();
                Parameters parameters = parametersBuilder.hasFavoriteNumbers(favoriteNumbers)
                        .withTariffingType(tariffingType)
                        .withConnectionFee(connectionFee)
                        .build();
                TariffBuilder tariffBuilder = new TariffBuilder(id);
                Tariff tariff = tariffBuilder.withName(name)
                        .withOperatorName(operatorName)
                        .withPayroll(payroll)
                        .withCallPrices(callPrices)
                        .withSmsPrice(smsPrice)
                        .withParameters(parameters)
                        .build();
                tariffs.add(tariff);
            }
        } catch (ParserConfigurationException e) {
            throw new ParsingException("Parser configuration error: " + e.getMessage());
        } catch (SAXException e) {
            throw new ParsingException("SAX error: " + e.getMessage());
        } catch (IOException e) {
            throw new ParsingException("I/O error: " + e.getMessage());
        }
        return tariffs;
    }

    public String getElement(Element element, String name) {
        return element.getElementsByTagName(name)
                .item(0)
                .getTextContent();
    }
}
