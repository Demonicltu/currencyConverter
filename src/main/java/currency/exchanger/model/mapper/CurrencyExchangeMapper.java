package currency.exchanger.model.mapper;

import currency.exchanger.model.dto.CurrencyExchangeRequest;
import currency.exchanger.model.dto.CurrencyExchangeResponse;

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
