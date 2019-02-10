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

import static org.testng.Assert.*;

public class SaxParserTest {

    private SaxParser parser = SaxParser.getInstance();
    private List<Tariff> expected = new ArrayList<>();

    @BeforeClass
    public void init() {
        CallPrices callPrices1 = new CallPricesBuilder()
                .withPriceWithinNetwork(0)
                .withPriceToOtherNetworks(0.09)
                .withPriceToLandlinePhones(0.09)
                .build();
        Parameters parameters1 = new ParametersBuilder()
                .hasFavoriteNumbers(1)
                .withTariffingType(TariffingType.SEC_60)
                .withConnectionFee(0)
                .withLaunchDate("2018-01-07")
                .build();
        Tariff tariff1 = new TariffBuilder("ID-3")
                .withName("Social")
                .withOperatorName("Velcom")
                .withPayroll(1.58)
                .withCallPrices(callPrices1)
                .withSmsPrice(0.046)
                .withParameters(parameters1)
                .build();
        CallPrices callPrices2 = new CallPricesBuilder()
                .withPriceWithinNetwork(0)
                .withPriceToOtherNetworks(0.1)
                .withPriceToLandlinePhones(0.12)
                .build();
        Parameters parameters2 = new ParametersBuilder()
                .hasFavoriteNumbers(3)
                .withTariffingType(TariffingType.SEC_60)
                .withConnectionFee(4)
                .withLaunchDate("2016-11-29")
                .build();
        Tariff tariff2 = new TariffBuilder("ID-4")
                .withName("XL")
                .withOperatorName("Life")
                .withPayroll(15)
                .withCallPrices(callPrices2)
                .withSmsPrice(0.048)
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
        InputStream inputStream = new FileInputStream("src/main/resources/testSax.xml");
        //when
        List<Tariff> result = parser.parse(inputStream);
        //then
        assertEquals(result, expected);
    }
}