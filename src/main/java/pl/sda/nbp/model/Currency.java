package pl.sda.nbp.model;

import java.util.List;

import lombok.Data;

@Data
public class Currency {

    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;

}
