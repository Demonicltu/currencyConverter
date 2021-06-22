package currency.exchanger.model.entity;

import com.opencsv.bean.CsvBindByName;

public class Currency {

    @CsvBindByName
    private String name;

    @CsvBindByName
    private double exchangeRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
