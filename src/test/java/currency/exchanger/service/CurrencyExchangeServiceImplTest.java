package currency.exchanger.service;

import currency.exchanger.data.CurrencyExchangeMock;
import currency.exchanger.dto.CurrencyExchangeResponse;
import currency.exchanger.exception.CannotExchangeException;
import currency.exchanger.exception.CurrencyNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CurrencyExchangeServiceImplTest {

    @InjectMocks
    private CurrencyExchangeServiceImpl currencyExchangeService;

    @Test
    void exchangeCurrency() throws Exception {
        CurrencyExchangeResponse response = currencyExchangeService.exchangeCurrency(CurrencyExchangeMock.getExchangeRequest());

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getFromCurrency().isEmpty());
        Assertions.assertFalse(response.getToCurrency().isEmpty());
        Assertions.assertTrue(response.getToQuantity() > 0);
        Assertions.assertTrue(response.getToQuantity() > 0);
    }

    @Test
    void exchangeCurrencyCannotExchangeException() {
        Assertions.assertThrows(
                CannotExchangeException.class,
                () -> currencyExchangeService.exchangeCurrency(CurrencyExchangeMock.getExchangeRequestSameCurrency())
        );
    }

    @Test
    void exchangeCurrencyCurrencyNotFoundException() {
        Assertions.assertThrows(
                CurrencyNotFoundException.class,
                () -> currencyExchangeService.exchangeCurrency(CurrencyExchangeMock.getExchangeRequestNotExistingCurrencies())
        );
    }

}
