package currency.exchanger.mapper;

import currency.exchanger.dto.CurrencyExchangeRequest;
import currency.exchanger.dto.CurrencyExchangeResponse;

public class CurrencyExchangeMapper {

    public static CurrencyExchangeResponse toExchangeDTOResponse(
            CurrencyExchangeRequest exchangeRequest,
            double toQuantity
    ) {
        return new CurrencyExchangeResponse(
                exchangeRequest.getFromCurrency(),
                exchangeRequest.getToCurrency(),
                exchangeRequest.getFromQuantity(),
                toQuantity
        );
    }

}
