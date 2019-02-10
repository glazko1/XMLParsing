package builder;

import entity.Parameters;
import entity.Tariff;
import entity.TariffingType;

public class ParametersBuilder {

    private int favoriteNumbers;
    private TariffingType tariffingType;
    private double connectionFee;
    private String launchDate;

    /**
     * Sets amount of favorite numbers in accordance with given parameter.
     * @param favoriteNumbers amount of favorite numbers.
     * @return current builder.
     */
    public ParametersBuilder hasFavoriteNumbers(int favoriteNumbers) {
        this.favoriteNumbers = favoriteNumbers;
        return this;
    }

    /**
     * Sets tariffing type (12 or 60-second) in accordance with given parameter.
     * @param tariffingType tariffing type (12 or 60-second).
     * @return current builder.
     */
    public ParametersBuilder withTariffingType(TariffingType tariffingType) {
        this.tariffingType = tariffingType;
        return this;
    }

    /**
     * Sets tariff's connection fee in accordance with given parameter.
     * @param connectionFee tariff's connection fee.
     * @return current builder.
     */
    public ParametersBuilder withConnectionFee(double connectionFee) {
        this.connectionFee = connectionFee;
        return this;
    }

    /**
     * Sets tariff's launch date in accordance with given parameter.
     * @param launchDate tariff's launch date.
     * @return current builder.
     */
    public ParametersBuilder withLaunchDate(String launchDate) {
        this.launchDate = launchDate;
        return this;
    }

    /**
     * Builds and returns {@link Parameters} object in accordance with set earlier
     * amount of favorite numbers, tariffing type, connection fee and launch date. One
     * or several fields may be skipped (e.g. when tariff does not have such parameters).
     * Later {@link Parameters} object is used as a part of {@link Tariff} object.
     * @return {@link Parameters} object with information about tariff's parameters.
     */
    public Parameters build() {
        Parameters parameters = new Parameters();
        parameters.setFavoriteNumbers(favoriteNumbers);
        parameters.setTariffingType(tariffingType);
        parameters.setConnectionFee(connectionFee);
        parameters.setLaunchDate(launchDate);
        return parameters;
    }

    /**
     * @return amount of favorite numbers.
     */
    public int getFavoriteNumbers() {
        return favoriteNumbers;
    }

    /**
     * @return tariffing type (12 or 60-second).
     */
    public TariffingType getTariffingType() {
        return tariffingType;
    }

    /**
     * @return connection fee.
     */
    public double getConnectionFee() {
        return connectionFee;
    }

    /**
     * @return launch date.
     */
    public String getLaunchDate() {
        return launchDate;
    }
}
