package com.example.springboot;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReportItem {

    private String symbol;
    private BigDecimal average;
    private BigDecimal percent;


    @Override
    public String toString() {
        return "ReportItem{" +
                "symbol='" + symbol + '\'' +
                ", average=" + average +
                ", percent=" + percent +
                '}';
    }
}
