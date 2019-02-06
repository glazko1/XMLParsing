package builder;

import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;
import entity.TariffingType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TariffBuilderTest {

    private TariffBuilder builder = new TariffBuilder("ID-5");
    private CallPrices readyCallPrices = new CallPrices();
    private Parameters readyParameters = new Parameters();
    private TariffBuilder readyBuilder = new TariffBuilder("ID-59");
    private Tariff readyTariff = new Tariff("ID-20");

    @BeforeClass
    private void init() {
        readyCallPrices = new CallPricesBuilder()
                .withPriceWithinNetwork(0.07)
                .withPriceToOtherNetworks(0.07)
                .withPriceToLandlinePhones(0.14)
                .build();
        readyParameters = new ParametersBuilder()
                .hasFavoriteNumbers(3)
                .withTariffingType(TariffingType.SEC_60)
                .withConnectionFee(3.0)
                .build();
        readyBuilder.withName("Start")
                .withOperatorName("Life")
                .withPayroll(1.5)
                .withCallPrices(readyCallPrices)
                .withSmsPrice(0.048)
                .withParameters(readyParameters);
        readyTariff.setName("Start");
        readyTariff.setOperatorName("Life");
        readyTariff.setPayroll(1.5);
        readyTariff.setCallPrices(readyCallPrices);
        readyTariff.setSmsPrice(0.048);
        readyTariff.setParameters(readyParameters);
    }

    @Test
    public void withName_correctParameters_true() {
        //given
        //when
        builder.withName("Super WEB 5");
        //then
        assertEquals(builder.getName(), "Super WEB 5");
    }

    @Test
    public void withOperatorName_correctParameters_true() {
        //given
        //when
        builder.withOperatorName("Velcom");
        //then
        assertEquals(builder.getOperatorName(), "Velcom");
    }

    @Test
    public void withPayroll_correctParameters_true() {
        //given
        //when
        builder.withPayroll(8.99);
        //then
        assertEquals(builder.getPayroll(), 8.99);
    }

    @Test
    public void withCallPrices_correctParameters_true() {
        //given
        //when
        builder.withCallPrices(readyCallPrices);
        //then
        assertEquals(builder.getCallPrices(), readyCallPrices);
    }

    @Test
    public void withSmsPrice_correctParameters_true() {
        //given
        //when
        builder.withSmsPrice(0.046);
        //then
        assertEquals(builder.getSmsPrice(), 0.046);
    }

    @Test
    public void withParameters_correctParameters_true() {
        //given
        //when
        builder.withParameters(readyParameters);
        //then
        assertEquals(builder.getParameters(), readyParameters);
    }

    @Test
    public void build_readyBuilder_true() {
        //given
        //when
        Tariff tariff = readyBuilder.build();
        //then
        assertEquals(tariff, readyTariff);
    }
}