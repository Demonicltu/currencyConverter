package currency.exchanger.service;

import currency.exchanger.dto.CurrencyExchangeRequest;
import currency.exchanger.dto.CurrencyExchangeResponse;
import currency.exchanger.exception.CannotExchangeException;
import currency.exchanger.exception.CurrencyNotFoundException;

public interface CurrencyExchangeService {

    CurrencyExchangeResponse exchangeCurrency(CurrencyExchangeRequest exchangeRequest)
            throws CurrencyNotFoundException, CannotExchangeException;

}
