package pl.sda.nbp.model;

import lombok.Data;

@Data
public class CurrencyRequest {

    private String currencyCode;
    private String from;
    private String to;

}
