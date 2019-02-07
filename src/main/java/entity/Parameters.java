package entity;

import java.util.Objects;

public class Parameters {

    private int favoriteNumbers;
    private TariffingType tariffingType;
    private double connectionFee;

    public int getFavoriteNumbers() {
        return favoriteNumbers;
    }

    public void setFavoriteNumbers(int favoriteNumbers) {
        this.favoriteNumbers = favoriteNumbers;
    }

    public String getTariffingType() {
        return tariffingType == TariffingType.SEC_60 ? "60-sec" : "12-sec";
    }

    public void setTariffingType(TariffingType tariffingType) {
        this.tariffingType = tariffingType;
    }

    public double getConnectionFee() {
        return connectionFee;
    }

    public void setConnectionFee(double connectionFee) {
        this.connectionFee = connectionFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Parameters that = (Parameters) o;
        return favoriteNumbers == that.favoriteNumbers &&
                Double.compare(that.connectionFee, connectionFee) == 0 &&
                tariffingType == that.tariffingType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteNumbers, tariffingType, connectionFee);
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "favoriteNumbers=" + favoriteNumbers +
                ", tariffingType=" + tariffingType +
                ", connectionFee=" + connectionFee +
                '}';
    }
}
