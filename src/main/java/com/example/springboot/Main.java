package com.example.springboot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URL;
import java.util.List;


public class Main {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        List<PurchaseItem> purchaseList = getPurchases();
        List<StockData> stockDataList = getStockData();

        var report = createReport(purchaseList, stockDataList);
        saveReport(report);

        for (var reportItem : report.getReportItems()) {
            System.out.println("item= " + reportItem);
        }


    }

    private static List<StockData> getStockData() throws Exception {
        var file = getFileFromResource("stock_data.json");

        List<StockData> stockData = objectMapper.readValue(file, new TypeReference<>() {});

        System.out.println(stockData);

        return stockData;

    }

    private static List<PurchaseItem> getPurchases() throws Exception {
        var file = getFileFromResource("purchases.json");

        List<PurchaseItem> jsonObject = objectMapper.readValue(file, new TypeReference<>() {});

        for (var item : jsonObject) {
            System.out.println("item= " + item);
        }

        return jsonObject;
    }

    private static File getFileFromResource(String fileName) throws Exception {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    public static GainLossReport createReport(List<PurchaseItem> purchaseList, List<StockData> stockDataList) {
        var report = new GainLossReport();

        for (var purchaseItem : purchaseList) {
            System.out.println("symbol= " + purchaseItem.getSymbol());

            StockData foundStockData = null;
            for (StockData stockData : stockDataList) {
                if (stockData.getDate().getSymbol().equals(purchaseItem.getSymbol())) {
                    foundStockData = stockData;
                    break;
                }
            }

            var monthly = foundStockData;
            var average = monthly.getAverage();
            var percentage = monthly.getGainLossPercentage(purchaseItem.getPurchasePrice());

            var reportItem = new ReportItem();
            reportItem.setSymbol(purchaseItem.getSymbol());
            reportItem.setAverage(average);
            reportItem.setPercent(percentage);

            report.addItem(reportItem);
        }

        return report;
    }

    public static void saveReport(GainLossReport report) throws Exception {
        objectMapper.writeValue(new File("report.json"), report);
    }


}
