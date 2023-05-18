package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;


@JsonIgnoreProperties
@Getter
@Setter
public class StockData {

    @JsonProperty("Meta Data")
    MetaData date;

    @JsonProperty("Monthly Time Series")
    private Map<String, MonthlyData> monthlyDataMap;

    public int size() {
        return monthlyDataMap.size();
    }

    public BigDecimal getAverage() {
        var sum = BigDecimal.ZERO;

        for (String key : monthlyDataMap.keySet()) {
            MonthlyData data = monthlyDataMap.get(key);
            sum = sum.add(data.getClose());
        }

        var count = BigDecimal.valueOf(size());
        return sum.divide(count, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getGainLossPercentage(BigDecimal originalPurchasePrice) {
        //Net Gain or Net Loss = (Current Price - Original Purchase Price) ÷ Original Purchase Price x 100
        var firstEntry = monthlyDataMap.entrySet().iterator().next();
        var firstStockData = firstEntry.getValue();
        var currentPrice = firstStockData.getClose();

        return currentPrice.subtract(originalPurchasePrice)
                .divide(originalPurchasePrice, RoundingMode.HALF_EVEN)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);

    }

    @Override
    public String toString() {
        return "StockData{" +
                "date=" + date +
                ", monthlyDataMap=" + monthlyDataMap +
                '}';
    }
}
