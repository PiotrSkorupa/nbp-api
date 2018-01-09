package pl.sda.nbp.view;

import java.util.Scanner;

import org.apache.http.impl.client.HttpClientBuilder;

import pl.sda.nbp.model.Currency;
import pl.sda.nbp.model.CurrencyRequest;
import pl.sda.nbp.service.NBPRestService;
import pl.sda.nbp.service.NBPService;
import pl.sda.nbp.validate.CurrencyRequestValidator;

public class QuoteMenu implements Displayable {

    private NBPService nbpService;

    public QuoteMenu() {
        nbpService = new NBPRestService(HttpClientBuilder.create()
                .build(), new CurrencyRequestValidator());
    }

    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj kod waluty");
        System.out.print(">");
        String currency = scanner.nextLine();
        System.out.println("Podaj od kiedy 'yyyy-dd-MM'");
        System.out.print(">");
        String from = scanner.nextLine();
        System.out.println("Podaj do kiedy 'yyyy-dd-MM'");
        System.out.print(">");
        String to = scanner.nextLine();

        CurrencyRequest currencyRequest = new CurrencyRequest();
        currencyRequest.setCurrencyCode(currency);
        currencyRequest.setFrom(from);
        currencyRequest.setTo(to);

        Currency response = nbpService.getCurrency(currencyRequest);

        System.out.println(response);
    }
}
