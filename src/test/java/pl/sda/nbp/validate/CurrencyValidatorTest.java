package pl.sda.nbp.validate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.sda.nbp.model.CurrencyRequest;

public class CurrencyValidatorTest {

    private CurrencyValidator currencyValidator;

    @Before
    public void setUp() {
        currencyValidator = new CurrencyRequestValidator();
    }

    // 2
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenCurrencyCodeIsEmpty() {

        // 2
        // expectedEx.expect(IllegalArgumentException.class);
        // expectedEx.expectMessage("Invalid currency code");

        // 1
        CurrencyRequest currencyRequest = new CurrencyRequest();
        currencyRequest.setFrom("2018-01-02");
        currencyRequest.setTo("2018-01-05");
        currencyRequest.setCurrencyCode("");

        try {
            currencyValidator.validate(currencyRequest);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Invalid currency code", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenNullIsPassedIn() {

        try {
            currencyValidator.validate(null);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("CurrencyRequest can not be null", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenFromDateIsWrongFormat() {

        CurrencyRequest currencyRequest = new CurrencyRequest();
        currencyRequest.setFrom("2018/01/02");
        currencyRequest.setTo("2018-01-05");
        currencyRequest.setCurrencyCode("usd");

        try {
            currencyValidator.validate(currencyRequest);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Date should be formatted 'yyyy-MM-dd'", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenToDateIsWrongFormatted() {
        CurrencyRequest currencyRequest = new CurrencyRequest();
        currencyRequest.setFrom("2018-01-02");
        currencyRequest.setTo("sdf");
        currencyRequest.setCurrencyCode("eur");

        try {
            currencyValidator.validate(currencyRequest);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Date should be formatted 'yyyy-MM-dd'", e.getMessage());
        }

    }

}