package builder;

import entity.CallPrices;
import entity.Tariff;

public class CallPricesBuilder {

    private double withinNetwork;
    private double otherNetworks;
    private double landlinePhones;

    /**
     * Sets call price within network according to given parameter.
     * @param withinNetwork call price within network.
     * @return current builder.
     */
    public CallPricesBuilder withPriceWithinNetwork(double withinNetwork) {
        this.withinNetwork = withinNetwork;
        return this;
    }

    /**
     * Sets call price to other networks according to given parameter.
     * @param otherNetworks call price to other networks.
     * @return current builder.
     */
    public CallPricesBuilder withPriceToOtherNetworks(double otherNetworks) {
        this.otherNetworks = otherNetworks;
        return this;
    }

    /**
     * Sets call price to landline phones according to given parameter.
     * @param landlinePhones call price to landline phones.
     * @return current builder.
     */
    public CallPricesBuilder withPriceToLandlinePhones(double landlinePhones) {
        this.landlinePhones = landlinePhones;
        return this;
    }

    /**
     * Builds and returns {@link CallPrices} object in accordance with set earlier
     * call prices (within network, to other networks and to other networks). One
     * or several fields may be skipped (e.g. when tariff does not have such functions).
     * Later {@link CallPrices} object is used as a part of {@link Tariff} object.
     * @return {@link CallPrices} object with information about call prices.
     */
    public CallPrices build() {
        CallPrices callPrices = new CallPrices();
        callPrices.setWithinNetwork(withinNetwork);
        callPrices.setOtherNetworks(otherNetworks);
        callPrices.setLandlinePhones(landlinePhones);
        return callPrices;
    }

    /**
     * @return call price within network.
     */
    public double getPriceWithinNetwork() {
        return withinNetwork;
    }

    /**
     * @return call price to other networks.
     */
    public double getPriceToOtherNetworks() {
        return otherNetworks;
    }

    /**
     * @return call price to landline phones.
     */
    public double getPriceToLandlinePhones() {
        return landlinePhones;
    }
}
