package rate.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
public class Rate {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @JsonProperty("date")
    @Temporal(TemporalType.DATE)
    private Date exchangeDate;
    private String gbp;
    private String usd;
    private String hkd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getGbp() {
        return gbp;
    }

    public void setGbp(String gbp) {
        this.gbp = gbp;
    }

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getHkd() {
        return hkd;
    }

    public void setHkd(String hkd) {
        this.hkd = hkd;
    }

    @JsonProperty("rates")
    private void unpackNameFromNestedObject(Map<String, String> rates) {
        gbp = rates.get("GBP");
        usd = rates.get("USD");
        hkd = rates.get("HKD");
    }

}
