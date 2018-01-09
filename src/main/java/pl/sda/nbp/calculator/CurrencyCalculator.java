package pl.sda.nbp.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import pl.sda.nbp.model.Currency;
import pl.sda.nbp.model.Rate;

public class CurrencyCalculator implements CurrencyCalculable {
    @Override
    public BigDecimal calculateAverage(Currency currency) {
        /*
         * OptionalDouble average = currency.getRates() .stream() .map(Rate::getMid)
         * .mapToDouble(BigDecimal::doubleValue) .average();
         */

        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        for (Rate rate : currency.getRates()) {

            BigDecimal mid = rate.getMid();
            sum = sum.add(mid);
        }

        int amount = currency.getRates()
                .size();

        BigDecimal result = sum.divide(new BigDecimal(amount));
        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
