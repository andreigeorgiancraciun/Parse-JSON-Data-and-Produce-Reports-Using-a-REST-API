package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class MonthlyData {

    @JsonProperty("1. open")
    String open;

    @JsonProperty("2. high")
    String high;

    @JsonProperty("3. low")
    String low;

    @JsonProperty("4. close")
    BigDecimal close;

    @JsonProperty("5. volume")
    String volume;

    @Override
    public String toString() {
        return "MonthlyData{" +
                "open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
