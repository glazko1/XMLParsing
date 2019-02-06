package builder;

import entity.Parameters;
import entity.TariffingType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ParametersBuilderTest {

    private ParametersBuilder builder = new ParametersBuilder();
    private ParametersBuilder readyBuilder = new ParametersBuilder();
    private Parameters readyParameters = new Parameters();

    @BeforeClass
    public void init() {
        readyBuilder.hasFavoriteNumbers(5)
                .withTariffingType(TariffingType.SEC_60)
                .withConnectionFee(0.99);
        readyParameters.setFavoriteNumbers(5);
        readyParameters.setTariffingType(TariffingType.SEC_60);
        readyParameters.setConnectionFee(0.99);
    }

    @Test
    public void hasFavoriteNumbers_correctParameters_true() {
        //given
        //when
        builder.hasFavoriteNumbers(3);
        //then
        assertEquals(builder.getFavoriteNumbers(), 3);
    }

    @Test
    public void withTariffingType_correctParameters_true() {
        //given
        //when
        builder.withTariffingType(TariffingType.SEC_60);
        //then
        assertEquals(builder.getTariffingType(), TariffingType.SEC_60);
    }

    @Test
    public void withConnectionFee_correctParameters_true() {
        //given
        //when
        builder.withConnectionFee(4.95);
        //then
        assertEquals(builder.getConnectionFee(), 4.95);
    }

    @Test
    public void build_readyBuilder_true() {
        //given
        //when
        Parameters parameters = readyBuilder.build();
        //then
        assertEquals(parameters, readyParameters);
    }
}