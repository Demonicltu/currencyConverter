package currency.exchanger.service;

import currency.exchanger.model.dto.CurrencyExchangeRequest;
import currency.exchanger.model.dto.CurrencyExchangeResponse;
import currency.exchanger.model.entity.Currency;
import currency.exchanger.exception.CannotExchangeException;
import currency.exchanger.exception.CurrencyNotFoundException;
import currency.exchanger.model.mapper.CurrencyExchangeMapper;
import currency.exchanger.holder.CurrenciesHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    public CurrencyExchangeResponse exchangeCurrency(CurrencyExchangeRequest exchangeRequest)
            throws CurrencyNotFoundException, CannotExchangeException {
        Currency fromCurrency = getCurrency(exchangeRequest.getFromCurrency());
        Currency toCurrency = getCurrency(exchangeRequest.getToCurrency());

        double convertedQuantity = convertCurrency(
                fromCurrency,
                toCurrency,
                exchangeRequest.getFromQuantity()
        );

        return CurrencyExchangeMapper.toExchangeDTOResponse(
                exchangeRequest,
                convertedQuantity
        );
    }

    private double convertCurrency(Currency fromCurrency, Currency toCurrency, double fromQuantity)
            throws CannotExchangeException {
        if (fromCurrency.getName().equals(toCurrency.getName())) {
            throw new CannotExchangeException();
        }

        return fromCurrency.getExchangeRate() / toCurrency.getExchangeRate() * fromQuantity;
    }

    private Currency getCurrency(String currencyName) throws CurrencyNotFoundException {
        try {
            return CurrenciesHolder.getInstance().getCurrencyByName(currencyName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file");
        }
    }

}
