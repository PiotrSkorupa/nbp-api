package pl.sda.nbp.validate;

import pl.sda.nbp.model.CurrencyRequest;

public interface CurrencyValidator {

    void validate(CurrencyRequest currencyRequest);
}
