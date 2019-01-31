package builder;

import entity.CallPrices;

public class CallPricesBuilder {

    private double withinNetwork;
    private double otherNetworks;
    private double landlinePhones;

    public CallPricesBuilder withPriceWithinNetwork(double withinNetwork) {
        this.withinNetwork = withinNetwork;
        return this;
    }

    public CallPricesBuilder withPriceToOtherNetworks(double otherNetworks) {
        this.otherNetworks = otherNetworks;
        return this;
    }

    public CallPricesBuilder withPriceToLandlinePhones(double landlinePhones) {
        this.landlinePhones = landlinePhones;
        return this;
    }

    public CallPrices build() {
        CallPrices callPrices = new CallPrices();
        callPrices.setWithinNetwork(withinNetwork);
        callPrices.setOtherNetworks(otherNetworks);
        callPrices.setLandlinePhones(landlinePhones);
        return callPrices;
    }
}
