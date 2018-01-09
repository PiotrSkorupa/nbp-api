package pl.sda.nbp.calculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.sda.nbp.model.Currency;
import pl.sda.nbp.model.Rate;

public class CurrencyCalculatorTest {

    private CurrencyCalculable currencyCalculable;

    @Before
    public void setUp() {
        currencyCalculable = new CurrencyCalculator();
    }

    @Test
    public void shouldCalculateAverage() {
        Currency defaultCurrency = getDefaultCurrency();

        BigDecimal actual = currencyCalculable.calculateAverage(defaultCurrency);

        Assert.assertEquals(new BigDecimal("4.33"), actual);
    }

    private Currency getDefaultCurrency() {

        List<Rate> rates = Arrays.asList(createRate("4.33"), createRate("4.32"), createRate("4.34"));
        Currency currency = new Currency();
        currency.setRates(rates);
        return currency;
    }

    private Rate createRate(String value) {
        Rate rate = new Rate();
        rate.setMid(new BigDecimal(value));
        return rate;
    }
}