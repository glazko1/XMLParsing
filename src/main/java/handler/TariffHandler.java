package handler;

import builder.CallPricesBuilder;
import builder.ParametersBuilder;
import builder.TariffBuilder;
import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;
import entity.TariffingType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class TariffHandler extends DefaultHandler {

    private List<Tariff> tariffs = new ArrayList<>();
    private TariffBuilder tariffBuilder;
    private CallPricesBuilder callPricesBuilder;
    private ParametersBuilder parametersBuilder;
    private StringBuilder data;

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

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "tariff":
                String id = attributes.getValue("id");
                tariffBuilder = new TariffBuilder(id);
                callPricesBuilder = new CallPricesBuilder();
                parametersBuilder = new ParametersBuilder();
                break;
            case "name":
                bName = true;
                break;
            case "operator-name":
                bOperatorName = true;
                break;
            case "payroll":
                bPayroll = true;
                break;
            case "within-network":
                bWithinNetwork = true;
                break;
            case "other-networks":
                bOtherNetworks = true;
                break;
            case "landline-phones":
                bLandlinePhones = true;
                break;
            case "sms-price":
                bSmsPrice = true;
                break;
            case "favorite-numbers":
                bFavoriteNumbers = true;
                break;
            case "tariffing":
                bTariffing = true;
                break;
            case "connection-fee":
                bConnectionFee = true;
                break;
            default:
                break;
        }
        data = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bName) {
            tariffBuilder.withName(data.toString());
            bName = false;
        } else if (bOperatorName) {
            tariffBuilder.withOperatorName(data.toString());
            bOperatorName = false;
        } else if (bPayroll) {
            tariffBuilder.withPayroll(Double.parseDouble(data.toString()));
            bPayroll = false;
        } else if (bWithinNetwork) {
            callPricesBuilder.withPriceWithinNetwork(Double.parseDouble(data.toString()));
            bWithinNetwork = false;
        } else if (bOtherNetworks) {
            callPricesBuilder.withPriceToOtherNetworks(Double.parseDouble(data.toString()));
            bOtherNetworks = false;
        } else if (bLandlinePhones) {
            callPricesBuilder.withPriceToLandlinePhones(Double.parseDouble(data.toString()));
            bLandlinePhones = false;
        } else if (bSmsPrice) {
            tariffBuilder.withSmsPrice(Double.parseDouble(data.toString()));
            bSmsPrice = false;
        } else if (bFavoriteNumbers) {
            parametersBuilder.hasFavoriteNumbers(Integer.parseInt(data.toString()));
            bFavoriteNumbers = false;
        } else if (bTariffing) {
            TariffingType tariffingType = "60-sec".equals(data.toString()) ? TariffingType.SEC_60 : TariffingType.SEC_12;
            parametersBuilder.withTariffingType(tariffingType);
            bTariffing = false;
        } else if (bConnectionFee) {
            parametersBuilder.withConnectionFee(Double.parseDouble(data.toString()));
            bConnectionFee = false;
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
