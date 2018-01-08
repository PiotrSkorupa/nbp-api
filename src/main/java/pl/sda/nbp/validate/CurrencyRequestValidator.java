package pl.sda.nbp.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.sda.nbp.model.CurrencyRequest;

public class CurrencyRequestValidator implements CurrencyValidator {

    private Set<String> allowedCurrencyCode;
    private SimpleDateFormat simpleDateFormat;

    public CurrencyRequestValidator() {
        allowedCurrencyCode = Stream.of("eur", "usd", "chf", "gbp")
                .collect(Collectors.toSet());
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void validate(CurrencyRequest currencyRequest) {

        if (currencyRequest == null) {
            throw new IllegalArgumentException("CurrencyRequest can not be null");
        }

        if (!allowedCurrencyCode.contains(currencyRequest.getCurrencyCode())) {
            throw new IllegalArgumentException("Invalid currency code");
        }

        validateDate(currencyRequest.getFrom());
        validateDate(currencyRequest.getTo());
    }

    private void validateDate(String date) {
        try {
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date should be formatted 'yyyy-MM-dd'");
        }
    }
}
