package builder;

import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;

public class TariffBuilder {

    private String id;
    private String name;
    private String operatorName;
    private double payroll;
    private CallPrices callPrices;
    private double smsPrice;
    private Parameters parameters;

    public TariffBuilder(String id) {
        this.id = id;
    }

    public TariffBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TariffBuilder withOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public TariffBuilder withPayroll(double payroll) {
        this.payroll = payroll;
        return this;
    }

    public TariffBuilder withCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
        return this;
    }

    public TariffBuilder withSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
        return this;
    }

    public TariffBuilder withParameters(Parameters parameters) {
        this.parameters = parameters;
        return this;
    }

    public Tariff build() {
        Tariff tariff = new Tariff(id);
        tariff.setName(name);
        tariff.setOperatorName(operatorName);
        tariff.setPayroll(payroll);
        tariff.setCallPrices(callPrices);
        tariff.setSmsPrice(smsPrice);
        tariff.setParameters(parameters);
        return tariff;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getOperatorName() {
        return operatorName;
    }

    double getPayroll() {
        return payroll;
    }

    CallPrices getCallPrices() {
        return callPrices;
    }

    double getSmsPrice() {
        return smsPrice;
    }

    Parameters getParameters() {
        return parameters;
    }
}
