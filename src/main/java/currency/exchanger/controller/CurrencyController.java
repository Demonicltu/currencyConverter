package currency.exchanger.controller;


import currency.exchanger.model.dto.CurrencyExchangeRequest;
import currency.exchanger.model.dto.CurrencyExchangeResponse;
import currency.exchanger.error.ApplicationError;
import currency.exchanger.exception.CannotExchangeException;
import currency.exchanger.exception.CurrencyNotFoundException;
import currency.exchanger.exception.RequestException;
import currency.exchanger.service.CurrencyExchangeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/currency")
public class CurrencyController {

    private final CurrencyExchangeService currencyExchangeService;

    public CurrencyController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @PostMapping("/exchange")
    public CurrencyExchangeResponse exchangeCurrency(@RequestBody @Valid CurrencyExchangeRequest exchangeRequest) {
        try {
            return currencyExchangeService.exchangeCurrency(exchangeRequest);
        } catch (CurrencyNotFoundException e) {
            throw new RequestException(ApplicationError.NOT_FOUND_CURRENCY);
        } catch (CannotExchangeException e) {
            throw new RequestException(ApplicationError.EXCHANGE_NOT_POSSIBLE);
        }
    }

}
