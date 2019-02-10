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

public class StaxParserTest {

    private StaxParser parser = StaxParser.getInstance();
    private List<Tariff> expected = new ArrayList<>();

    @BeforeClass
    public void init() {
        CallPrices callPrices1 = new CallPricesBuilder()
                .withPriceWithinNetwork(0)
                .withPriceToOtherNetworks(0.11)
                .withPriceToLandlinePhones(0.13)
                .build();
        Parameters parameters1 = new ParametersBuilder()
                .hasFavoriteNumbers(3)
                .withTariffingType(TariffingType.SEC_60)
                .withConnectionFee(0)
                .withLaunchDate("2017-04-19")
                .build();
        Tariff tariff1 = new TariffBuilder("ID-5")
                .withName("Super")
                .withOperatorName("MTS")
                .withPayroll(10)
                .withCallPrices(callPrices1)
                .withSmsPrice(0.057)
                .withParameters(parameters1)
                .build();
        CallPrices callPrices2 = new CallPricesBuilder()
                .withPriceWithinNetwork(0)
                .withPriceToOtherNetworks(0.07)
                .withPriceToLandlinePhones(0.07)
                .build();
        Parameters parameters2 = new ParametersBuilder()
                .hasFavoriteNumbers(3)
                .withTariffingType(TariffingType.SEC_60)
                .withConnectionFee(5)
                .withLaunchDate("2018-01-15")
                .build();
        Tariff tariff2 = new TariffBuilder("ID-6")
                .withName("lemon")
                .withOperatorName("Velcom")
                .withPayroll(9.9)
                .withCallPrices(callPrices2)
                .withSmsPrice(0.046)
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
        InputStream inputStream = new FileInputStream("src/main/resources/testStax.xml");
        //when
        List<Tariff> result = parser.parse(inputStream);
        //then
        assertEquals(result, expected);
    }
}