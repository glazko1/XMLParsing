package builder;

import entity.CallPrices;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CallPricesBuilderTest {

    private CallPricesBuilder builder = new CallPricesBuilder();
    private CallPricesBuilder readyBuilder = new CallPricesBuilder();
    private CallPrices readyCallPrices = new CallPrices();

    @BeforeClass
    public void init() {
        readyBuilder.withPriceWithinNetwork(0.5)
                .withPriceToOtherNetworks(0.8)
                .withPriceToLandlinePhones(1.1);
        readyCallPrices.setWithinNetwork(0.5);
        readyCallPrices.setOtherNetworks(0.8);
        readyCallPrices.setLandlinePhones(1.1);
    }

    @Test
    public void withPriceWithinNetwork_correctParameters_true() {
        //given
        //when
        builder.withPriceWithinNetwork(1.0);
        //then
        assertEquals(builder.getPriceWithinNetwork(), 1.0);
    }

    @Test
    public void withPriceToOtherNetworks_correctParameters_true() {
        //given
        //when
        builder.withPriceToOtherNetworks(1.5);
        //then
        assertEquals(builder.getPriceToOtherNetworks(), 1.5);
    }

    @Test
    public void withPriceToLandlinePhones_correctParameters_true() {
        //given
        //when
        builder.withPriceToLandlinePhones(2.0);
        //then
        assertEquals(builder.getPriceToLandlinePhones(), 2.0);
    }

    @Test
    public void build_readyBuilder_true() {
        //given
        //when
        CallPrices callPrices = readyBuilder.build();
        //then
        assertEquals(callPrices, readyCallPrices);
    }
}