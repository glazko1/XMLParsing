package parser;

import builder.CallPricesBuilder;
import builder.ParametersBuilder;
import builder.TariffBuilder;
import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;
import entity.TariffingType;
import exception.ParsingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DomParserTest {

    private DomParser parser = DomParser.getInstance();
    private List<Tariff> expected = new ArrayList<>();

    @BeforeClass
    public void init() {
        CallPrices callPrices1 = new CallPricesBuilder()
                .withPriceWithinNetwork(0)
                .withPriceToOtherNetworks(0.15)
                .withPriceToLandlinePhones(0.2)
                .build();
        Parameters parameters1 = new ParametersBuilder()
                .hasFavoriteNumbers(5)
                .withTariffingType(TariffingType.SEC_60)
                .withConnectionFee(0)
                .withLaunchDate("2017-02-10")
                .build();
        Tariff tariff1 = new TariffBuilder("ID-1")
                .withName("Comfort")
                .withOperatorName("Velcom")
                .withPayroll(11.43)
                .withCallPrices(callPrices1)
                .withSmsPrice(0.046)
                .withParameters(parameters1)
                .build();
        CallPrices callPrices2 = new CallPricesBuilder()
                .withPriceWithinNetwork(0)
                .withPriceToOtherNetworks(0.08)
                .withPriceToLandlinePhones(0.12)
                .build();
        Parameters parameters2 = new ParametersBuilder()
                .hasFavoriteNumbers(3)
                .withTariffingType(TariffingType.SEC_60)
                .withConnectionFee(3)
                .withLaunchDate("2017-05-19")
                .build();
        Tariff tariff2 = new TariffBuilder("ID-2")
                .withName("4G mini")
                .withOperatorName("MTS")
                .withPayroll(12.6)
                .withCallPrices(callPrices2)
                .withSmsPrice(0.045)
                .withParameters(parameters2)
                .build();
        expected.add(tariff1);
        expected.add(tariff2);
    }

    @Test(expectedExceptions = ParsingException.class)
    public void parse_wrongParameters_parsingException() throws FileNotFoundException, ParsingException {
        //given
        InputStream inputStream = new FileInputStream("src/main/resources/badTariffs.xml");
        //when
        parser.parse(inputStream);
        //then
        //expecting ParsingException
    }

    @Test
    public void parse_correctParameters_true() throws FileNotFoundException, ParsingException {
        //given
        InputStream inputStream = new FileInputStream("src/main/resources/testDom.xml");
        //when
        List<Tariff> result = parser.parse(inputStream);
        //then
        assertEquals(result, expected);
    }
}