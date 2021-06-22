package currency.exchanger.service;

import currency.exchanger.model.dto.CurrencyExchangeRequest;
import currency.exchanger.model.dto.CurrencyExchangeResponse;
import currency.exchanger.exception.CannotExchangeException;
import currency.exchanger.exception.CurrencyNotFoundException;

public interface CurrencyExchangeService {

    CurrencyExchangeResponse exchangeCurrency(CurrencyExchangeRequest exchangeRequest)
            throws CurrencyNotFoundException, CannotExchangeException;

}
