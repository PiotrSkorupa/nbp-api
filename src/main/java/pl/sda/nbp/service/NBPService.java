package pl.sda.nbp.service;

import pl.sda.nbp.model.Currency;
import pl.sda.nbp.model.CurrencyRequest;

public interface NBPService {

    Currency getCurrency(CurrencyRequest currencyRequest);

}
