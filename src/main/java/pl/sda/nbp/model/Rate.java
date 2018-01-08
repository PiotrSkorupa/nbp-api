package pl.sda.nbp.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Rate {

    private String no;
    private String effectiveDate;
    private BigDecimal mid;

}
