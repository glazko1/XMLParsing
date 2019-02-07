package validator;

import exception.ValidationException;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.testng.Assert.assertTrue;

public class XmlValidatorTest {

    private XmlValidator validator = XmlValidator.getInstance();

    @Test
    public void validate_correctFiles_true() throws FileNotFoundException, ValidationException {
        //given
        InputStream xmlInputStream = new FileInputStream("src/main/resources/tariffs.xml");
        InputStream xsdInputStream = new FileInputStream("src/main/resources/schema.xsd");
        //when
        boolean result = validator.validate(xmlInputStream, xsdInputStream);
        //then
        assertTrue(result);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void validate_incorrectFiles_validationException() throws FileNotFoundException, ValidationException {
        //given
        InputStream xmlInputStream = new FileInputStream("src/main/resources/badTariffs.xml");
        InputStream xsdInputStream = new FileInputStream("src/main/resources/schema.xsd");
        //when
        boolean result = validator.validate(xmlInputStream, xsdInputStream);
        //then
        //expecting ValidationException
    }
}