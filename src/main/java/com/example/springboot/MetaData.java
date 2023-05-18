package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MetaData {

    @JsonProperty("1. Information")
    String info;

    @JsonProperty("2. Symbol")
    String symbol;

    @JsonProperty("3. Last Refreshed")
    String last;

    @JsonProperty("4. Time Zone")
    String timeZone;

    @Override
    public String toString() {
        return "MetaData{" +
                "info='" + info + '\'' +
                ", symbol='" + symbol + '\'' +
                ", last='" + last + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
