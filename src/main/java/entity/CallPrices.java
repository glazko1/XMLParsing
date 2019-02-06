package entity;

import java.util.Objects;

public class CallPrices {

    private double withinNetwork;
    private double otherNetworks;
    private double landlinePhones;

    public double getWithinNetwork() {
        return withinNetwork;
    }

    public void setWithinNetwork(double withinNetwork) {
        this.withinNetwork = withinNetwork;
    }

    public double getOtherNetworks() {
        return otherNetworks;
    }

    public void setOtherNetworks(double otherNetworks) {
        this.otherNetworks = otherNetworks;
    }

    public double getLandlinePhones() {
        return landlinePhones;
    }

    public void setLandlinePhones(double landlinePhones) {
        this.landlinePhones = landlinePhones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        CallPrices that = (CallPrices) o;
        return Double.compare(that.withinNetwork, withinNetwork) == 0 &&
                Double.compare(that.otherNetworks, otherNetworks) == 0 &&
                Double.compare(that.landlinePhones, landlinePhones) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(withinNetwork, otherNetworks, landlinePhones);
    }

    @Override
    public String toString() {
        return "CallPrices{" +
                "withinNetwork=" + withinNetwork +
                ", otherNetworks=" + otherNetworks +
                ", landlinePhones=" + landlinePhones +
                '}';
    }
}
