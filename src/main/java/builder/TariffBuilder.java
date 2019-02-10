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

    /**
     * Sets tariff's name in accordance with given parameter.
     * @param name name of tariff.
     * @return current builder.
     */
    public TariffBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets tariff operator's name in accordance with given parameter.
     * @param operatorName name of operator.
     * @return current builder.
     */
    public TariffBuilder withOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * Sets tariff's payroll in accordance with given parameter.
     * @param payroll payroll of tariff.
     * @return current builder.
     */
    public TariffBuilder withPayroll(double payroll) {
        this.payroll = payroll;
        return this;
    }

    /**
     * Sets tariff's call prices (within network, to other networks, to landline phones)
     * in accordance with given parameter.
     * @param callPrices tariff's call prices.
     * @return current builder.
     * @see CallPrices
     */
    public TariffBuilder withCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
        return this;
    }

    /**
     * Sets tariff's SMS prices in accordance with given parameter.
     * @param smsPrice tariff's SMS prices.
     * @return current builder.
     */
    public TariffBuilder withSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
        return this;
    }

    /**
     * Sets tariff's parameters (amount of favorite numbers, tariffing type, connection fee,
     * launch date) in accordance with given parameter.
     * @param parameters tariff's parameters.
     * @return current builder.
     * @see Parameters
     */
    public TariffBuilder withParameters(Parameters parameters) {
        this.parameters = parameters;
        return this;
    }

    /**
     * Builds and returns {@link Tariff} object in accordance with set earlier name, operator
     * name, payroll, call prices, SMS prices and parameters. One or several fields may be
     * skipped (e.g. when tariff does not have such parameters).
     * @return {@link Tariff} object with all information about tariff.
     * @see CallPrices
     * @see Parameters
     */
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

    /**
     * @return tariff's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return operator's name.
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @return tariff's payroll.
     */
    public double getPayroll() {
        return payroll;
    }

    /**
     * @return tariff's call prices.
     */
    public CallPrices getCallPrices() {
        return callPrices;
    }

    /**
     * @return tariff's SMS prices.
     */
    public double getSmsPrice() {
        return smsPrice;
    }

    /**
     * @return tariff's parameters.
     */
    public Parameters getParameters() {
        return parameters;
    }
}
