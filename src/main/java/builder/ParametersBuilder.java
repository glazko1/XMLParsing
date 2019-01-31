package builder;

import entity.Parameters;
import entity.TariffingType;

public class ParametersBuilder {

    private int favoriteNumbers;
    private TariffingType tariffingType;
    private double connectionFee;

    public ParametersBuilder hasFavoriteNumbers(int favoriteNumbers) {
        this.favoriteNumbers = favoriteNumbers;
        return this;
    }

    public ParametersBuilder withTariffingType(TariffingType tariffingType) {
        this.tariffingType = tariffingType;
        return this;
    }

    public ParametersBuilder withConnectionFee(double connectionFee) {
        this.connectionFee = connectionFee;
        return this;
    }

    public Parameters build() {
        Parameters parameters = new Parameters();
        parameters.setFavoriteNumbers(favoriteNumbers);
        parameters.setTariffingType(tariffingType);
        parameters.setConnectionFee(connectionFee);
        return parameters;
    }
}
