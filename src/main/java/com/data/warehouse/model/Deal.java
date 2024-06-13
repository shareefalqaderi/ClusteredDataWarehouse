package com.data.warehouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Deal")
public class Deal {

    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "FromCurrencyCode")
    private String fromCurrencyCode;

    @Column(name = "ToCurrencyCode")
    private String toCurrencyCode;

    @Column(name = "Timestamp")
    private String timestamp;

    @Column(name = "Amount")
    private String amount;

    public Deal() {
    }

    public Deal(Long id, String fromCurrencyCode, String toCurrencyCode, String timestamp, String amount) {
        this.id = id;
        this.fromCurrencyCode = fromCurrencyCode;
        this.toCurrencyCode = toCurrencyCode;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
