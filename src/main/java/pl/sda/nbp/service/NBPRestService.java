package pl.sda.nbp.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.sda.nbp.model.Currency;
import pl.sda.nbp.model.CurrencyRequest;
import pl.sda.nbp.validate.CurrencyRequestValidator;
import pl.sda.nbp.validate.CurrencyValidator;

public class NBPRestService implements NBPService {

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    private CurrencyValidator currencyValidator;

    private static final String NBP_URL_PATTERN = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s";

    public NBPRestService(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
        this.currencyValidator = new CurrencyRequestValidator();
    }

    public Currency getCurrency(CurrencyRequest currencyRequest) {
        currencyValidator.validate(currencyRequest);

        HttpUriRequest httpRequest = createHttpRequest(currencyRequest);
        try {
            HttpResponse execute = httpClient.execute(httpRequest);
            String content = IOUtils.toString(execute.getEntity()
                    .getContent(), "UTF-8");
            return objectMapper.readValue(content, Currency.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpUriRequest createHttpRequest(CurrencyRequest currencyRequest) {
        final String url = String.format(NBP_URL_PATTERN, currencyRequest.getCurrencyCode(), currencyRequest.getFrom(),
                currencyRequest.getTo());

        return new HttpGet(url);
    }

}
