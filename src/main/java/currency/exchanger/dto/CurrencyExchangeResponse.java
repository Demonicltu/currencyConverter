package currency.exchanger.dto;

import currency.exchanger.util.NumberFormatting;

public class CurrencyExchangeResponse {

    private final String fromCurrency;

    private final String toCurrency;

    private final double fromQuantity;

    private final double toQuantity;

    public CurrencyExchangeResponse(
            String fromCurrency,
            String toCurrency,
            double fromQuantity,
            double toQuantity
    ) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.fromQuantity = fromQuantity;
        this.toQuantity = NumberFormatting.truncateDouble(toQuantity);
    }

    public double getToQuantity() {
        return toQuantity;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getFromQuantity() {
        return fromQuantity;
    }

}
