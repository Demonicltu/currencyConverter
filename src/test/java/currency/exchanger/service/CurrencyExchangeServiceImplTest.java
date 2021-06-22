package currency.exchanger.service;

import currency.exchanger.data.CurrencyExchangeMock;
import currency.exchanger.model.dto.CurrencyExchangeResponse;
import currency.exchanger.exception.CannotExchangeException;
import currency.exchanger.exception.CurrencyNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CurrencyExchangeServiceImplTest {

    @InjectMocks
    private CurrencyExchangeServiceImpl currencyExchangeService;

    @Test
    void exchangeCurrency() throws Exception {
        CurrencyExchangeResponse response =
                currencyExchangeService.exchangeCurrency(CurrencyExchangeMock.getExchangeRequest());

        assertNotNull(response);
        assertFalse(response.getFromCurrency().isEmpty());
        assertFalse(response.getToCurrency().isEmpty());
        assertTrue(response.getToQuantity() > 0);
        assertTrue(response.getToQuantity() > 0);
    }

    @Test
    void exchangeCurrencyCannotExchangeException() {
        assertThrows(
                CannotExchangeException.class,
                () -> currencyExchangeService.exchangeCurrency(
                        CurrencyExchangeMock.getExchangeRequestSameCurrency()
                )
        );
    }

    @Test
    void exchangeCurrencyCurrencyNotFoundException() {
        assertThrows(
                CurrencyNotFoundException.class,
                () -> currencyExchangeService.exchangeCurrency(
                        CurrencyExchangeMock.getExchangeRequestNotExistingCurrencies()
                )
        );
    }

}
