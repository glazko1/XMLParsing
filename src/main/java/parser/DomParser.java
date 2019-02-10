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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements XmlParser {

    private static final DomParser INSTANCE = new DomParser();

    public static DomParser getInstance() {
        return INSTANCE;
    }

    private DomParser() {}

    private List<Tariff> tariffs;

    @Override
    public List<Tariff> parse(InputStream inputStream) throws ParsingException {
        tariffs = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("tariff");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                parseNode(node);
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

    /**
     * Parses one node (one tariff). Method is called by {@code parse()} method when
     * a new node was found. Splits given node into id, name, operator's name, payroll,
     * call prices (within network, to other networks, to landline phones), SMS prices
     * and parameters (amount of favorite numbers, tariffing type, connection fee and
     * launch date). Creates {@link Tariff} object with help of builders and adds it into
     * a list of tariffs.
     * @param node element to parse.
     */
    private void parseNode(Node node) {
        Element element = (Element) node;
        String id = getId(element);
        String name = getName(element);
        String operatorName = getOperatorName(element);
        double payroll = getPayroll(element);
        double withinNetwork = getWithinNetwork(element);
        double otherNetworks = getOtherNetworks(element);
        double landlinePhones = getLandLinePhones(element);
        double smsPrice = getSmsPrice(element);
        int favoriteNumbers = getFavoriteNumbers(element);
        TariffingType tariffingType = getTariffingType(element);
        double connectionFee = getConnectionFee(element);
        String launchDate = getLaunchDate(element);
        CallPricesBuilder callPricesBuilder = new CallPricesBuilder();
        CallPrices callPrices = callPricesBuilder.withPriceWithinNetwork(withinNetwork)
                .withPriceToOtherNetworks(otherNetworks)
                .withPriceToLandlinePhones(landlinePhones)
                .build();
        ParametersBuilder parametersBuilder = new ParametersBuilder();
        Parameters parameters = parametersBuilder.hasFavoriteNumbers(favoriteNumbers)
                .withTariffingType(tariffingType)
                .withConnectionFee(connectionFee)
                .withLaunchDate(launchDate)
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

    /**
     * @param element node with information about tariff.
     * @param name name of element.
     * @return element its by name.
     */
    private String getElement(Element element, String name) {
        return element.getElementsByTagName(name)
                .item(0)
                .getTextContent();
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's id.
     */
    private String getId(Element element) {
        return element.getAttribute("id");
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's name.
     */
    private String getName(Element element) {
        return getElement(element, "name");
    }

    /**
     * @param element node with information about tariff.
     * @return operator's name.
     */
    private String getOperatorName(Element element) {
        return getElement(element, "operator-name");
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's payroll.
     */
    private double getPayroll(Element element) {
        return Double.parseDouble(getElement(element, "payroll"));
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's call price within network.
     */
    private double getWithinNetwork(Element element) {
        return Double.parseDouble(getElement(element, "within-network"));
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's call price to other networks.
     */
    private double getOtherNetworks(Element element) {
        return Double.parseDouble(getElement(element, "other-networks"));
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's call price to landline phones.
     */
    private double getLandLinePhones(Element element) {
        return Double.parseDouble(getElement(element, "landline-phones"));
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's SMS price.
     */
    private double getSmsPrice(Element element) {
        return Double.parseDouble(getElement(element, "sms-price"));
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's amount of favorite numbers.
     */
    private int getFavoriteNumbers(Element element) {
        return Integer.parseInt(getElement(element, "favorite-numbers"));
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's tariffing type.
     */
    private TariffingType getTariffingType(Element element) {
        String tariffing = getElement(element, "tariffing");
        return "60-sec".equals(tariffing) ? TariffingType.SEC_60 : TariffingType.SEC_12;
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's connection fee.
     */
    private double getConnectionFee(Element element) {
        return Double.parseDouble(getElement(element, "connection-fee"));
    }

    /**
     * @param element node with information about tariff.
     * @return tariff's launch date.
     */
    private String getLaunchDate(Element element) {
        return getElement(element, "launch-date");
    }
}
