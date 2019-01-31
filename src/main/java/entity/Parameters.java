package entity;

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

    public TariffingType getTariffingType() {
        return tariffingType;
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
    public String toString() {
        return "Parameters{" +
                "favoriteNumbers=" + favoriteNumbers +
                ", tariffingType=" + tariffingType +
                ", connectionFee=" + connectionFee +
                '}';
    }
}
