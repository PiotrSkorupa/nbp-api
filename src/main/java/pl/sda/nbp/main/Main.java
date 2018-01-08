package pl.sda.nbp.main;

import org.apache.http.impl.client.HttpClientBuilder;
import pl.sda.nbp.model.Currency;
import pl.sda.nbp.model.CurrencyRequest;
import pl.sda.nbp.service.NBPRestService;
import pl.sda.nbp.service.NBPService;

public class Main {

    public static void main(String[] args) {

        NBPService nbpService = new NBPRestService(HttpClientBuilder.create().build());

        CurrencyRequest currencyRequest = new CurrencyRequest();
        currencyRequest.setCurrencyCode("eur");
        currencyRequest.setFrom("2017-12-22");
        currencyRequest.setTo("2017-12-28");
        currencyRequest.setCurrencyCode("eur");

        Currency currency = nbpService.getCurrency(currencyRequest);

        System.out.println(currency);

    }
}
