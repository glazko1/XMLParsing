package handler;

import builder.CallPricesBuilder;
import builder.ParametersBuilder;
import builder.TariffBuilder;
import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;
import entity.TariffParameter;
import entity.TariffingType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static entity.TariffParameter.CONNECTION_FEE;
import static entity.TariffParameter.FAVORITE_NUMBERS;
import static entity.TariffParameter.LANDLINE_PHONES;
import static entity.TariffParameter.NAME;
import static entity.TariffParameter.OPERATOR_NAME;
import static entity.TariffParameter.OTHER_NETWORKS;
import static entity.TariffParameter.PAYROLL;
import static entity.TariffParameter.SMS_PRICE;
import static entity.TariffParameter.TARIFFING;
import static entity.TariffParameter.WITHIN_NETWORK;

public class TariffHandler extends DefaultHandler {

    private List<Tariff> tariffs = new ArrayList<>();
    private TariffBuilder tariffBuilder;
    private CallPricesBuilder callPricesBuilder;
    private ParametersBuilder parametersBuilder;
    private StringBuilder data;

    private TariffParameter currentParameter;

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "tariff":
                String id = attributes.getValue("id");
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
            default:
                break;
        }
        data = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (currentParameter) {
            case NAME:
                tariffBuilder.withName(data.toString());
                break;
            case OPERATOR_NAME:
                tariffBuilder.withOperatorName(data.toString());
                break;
            case PAYROLL:
                tariffBuilder.withPayroll(Double.parseDouble(data.toString()));
                break;
            case WITHIN_NETWORK:
                callPricesBuilder.withPriceWithinNetwork(Double.parseDouble(data.toString()));
                break;
            case OTHER_NETWORKS:
                callPricesBuilder.withPriceToOtherNetworks(Double.parseDouble(data.toString()));
                break;
            case LANDLINE_PHONES:
                callPricesBuilder.withPriceToLandlinePhones(Double.parseDouble(data.toString()));
                break;
            case SMS_PRICE:
                tariffBuilder.withSmsPrice(Double.parseDouble(data.toString()));
                break;
            case FAVORITE_NUMBERS:
                parametersBuilder.hasFavoriteNumbers(Integer.parseInt(data.toString()));
                break;
            case TARIFFING:
                TariffingType tariffingType = "60-sec".equals(data.toString()) ? TariffingType.SEC_60 : TariffingType.SEC_12;
                parametersBuilder.withTariffingType(tariffingType);
                break;
            case CONNECTION_FEE:
                parametersBuilder.withConnectionFee(Double.parseDouble(data.toString()));
                break;
            default:
                break;
        }
        if ("tariff".equals(qName)) {
            CallPrices callPrices = callPricesBuilder.build();
            tariffBuilder.withCallPrices(callPrices);
            Parameters parameters = parametersBuilder.build();
            tariffBuilder.withParameters(parameters);
            Tariff tariff = tariffBuilder.build();
            tariffs.add(tariff);
        }
    }
}
