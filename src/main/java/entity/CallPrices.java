package entity;

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
    public String toString() {
        return "CallPrices{" +
                "withinNetwork=" + withinNetwork +
                ", otherNetworks=" + otherNetworks +
                ", landlinePhones=" + landlinePhones +
                '}';
    }
}
