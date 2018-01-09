package pl.sda.nbp.calculator;

import java.math.BigDecimal;

import pl.sda.nbp.model.Currency;

public interface CurrencyCalculable {

    BigDecimal calculateAverage(Currency currency);
}
