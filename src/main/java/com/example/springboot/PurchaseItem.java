package com.example.springboot;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseItem {

    String symbol;
    BigDecimal purchasePrice;
    String purchaseDate;

    @Override
    public String toString() {
        return "PurchaseItem{" +
                "symbol='" + symbol + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
