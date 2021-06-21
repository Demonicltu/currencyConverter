package currency.exchanger.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class CurrencyExchangeRequest {

    @NotNull(message = "Currency cannot be null")
    @Pattern(regexp = "^([A-Z]|[a-z]){3}$", message = "Currency should be 3 letters")
    private final String fromCurrency;

    @NotNull(message = "Currency cannot be null")
    @Pattern(regexp = "^([A-Z]|[a-z]){3}$", message = "Currency should be 3 letters")
    private final String toCurrency;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity should be bigger than 0")
    private final double fromQuantity;

    public CurrencyExchangeRequest(
            String fromCurrency,
            String toCurrency,
            double fromQuantity
    ) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.fromQuantity = fromQuantity;
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
