package com.example.springboot;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GainLossReport {

    private List<ReportItem> reportItems = new ArrayList<>();

    public void addItem(ReportItem toAdd) {
        reportItems.add(toAdd);
    }

}
