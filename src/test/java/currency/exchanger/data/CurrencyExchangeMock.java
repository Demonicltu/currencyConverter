package currency.exchanger.data;

import currency.exchanger.dto.CurrencyExchangeRequest;
import currency.exchanger.dto.CurrencyExchangeResponse;

public class CurrencyExchangeMock {

    public static CurrencyExchangeRequest getExchangeRequest() {
        return new CurrencyExchangeRequest(
                "USD",
                "BTC",
                100.59
        );
    }

    public static CurrencyExchangeRequest getExchangeRequestSameCurrency() {
        return new CurrencyExchangeRequest(
                "EUR",
                "EUR",
                100.5
        );
    }

    public static CurrencyExchangeRequest getExchangeRequestNotExistingCurrencies() {
        return new CurrencyExchangeRequest(
                "AAA",
                "BBB",
                100.5
        );
    }

    public static CurrencyExchangeResponse getExchangeResponse() {
        return new CurrencyExchangeResponse(
                "EUR",
                "USD",
                100.5,
                125
        );
    }

}
