package pl.sda.nbp.service;

import static org.mockito.MockitoAnnotations.initMocks;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;

import pl.sda.nbp.model.Currency;
import pl.sda.nbp.model.CurrencyRequest;
import pl.sda.nbp.model.Rate;

public class NBPRestServiceTest {

    private static final String DEFAULT_URL = "http://api.nbp.pl/api/exchangerates/rates/a/eur/2017-12-06/2017-12-07";

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse httpResponse;

    @Mock
    private HttpEntity httpEntity;

    private NBPService nbpService;

    @Before
    public void setUp() {
        initMocks(this);
        nbpService = new NBPRestService(httpClient);
    }

    @Test
    public void shouldReturnEurHappyPath() throws Exception {
        Currency expected = getDefaultCurrency();

        CurrencyRequest request = getDefaultCurrencyRequest();

        String json = getResourceAsString("/currency/default-eur-rs.json");

        HttpUriRequest httpRequest = getDefaultHttpRequest();

        Mockito.when(httpClient.execute(Mockito.argThat(createArgumentMatcher(httpRequest))))
                .thenReturn(httpResponse);
        Mockito.when(httpResponse.getEntity())
                .thenReturn(httpEntity);
        Mockito.when(httpEntity.getContent())
                .thenReturn(new ByteArrayInputStream(json.getBytes()));

        Currency actual = nbpService.getCurrency(request);

        Assert.assertEquals(expected, actual);

        Mockito.verify(httpClient, Mockito.times(1))
                .execute(Mockito.argThat(createArgumentMatcher(httpRequest)));
        Mockito.verify(httpResponse, Mockito.times(1))
                .getEntity();
        Mockito.verify(httpEntity, Mockito.times(1))
                .getContent();
    }

    private CurrencyRequest getDefaultCurrencyRequest() {
        CurrencyRequest currencyRequest = new CurrencyRequest();
        currencyRequest.setCurrencyCode("eur");
        currencyRequest.setFrom("2017-12-06");
        currencyRequest.setTo("2017-12-07");

        return currencyRequest;
    }

    private ArgumentMatcher<HttpUriRequest> createArgumentMatcher(HttpUriRequest expected) {
        return httpUriRequest -> Objects.equals(expected.getURI(), httpUriRequest.getURI());
    }

    private HttpUriRequest getDefaultHttpRequest() {
        return new HttpGet(DEFAULT_URL);
    }

    private Currency getDefaultCurrency() {
        Currency currency = new Currency();
        currency.setTable("A");
        currency.setCurrency("euro");
        currency.setCode("EUR");
        currency.setRates(getDefaultRates());

        return currency;
    }

    private List<Rate> getDefaultRates() {
        Rate firstRate = new Rate();
        firstRate.setNo("236/A/NBP/2017");
        firstRate.setEffectiveDate("2017-12-06");
        firstRate.setMid(new BigDecimal("4.2154"));

        Rate secondRate = new Rate();
        secondRate.setNo("237/A/NBP/2017");
        secondRate.setEffectiveDate("2017-12-07");
        secondRate.setMid(new BigDecimal("4.2115"));

        return Arrays.asList(firstRate, secondRate);
    }

    private String getResourceAsString(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(NBPRestServiceTest.class.getResource(path)
                .toURI())));
    }
}