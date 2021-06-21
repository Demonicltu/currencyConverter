package currency.exchanger.controller;

import currency.exchanger.data.CurrencyExchangeMock;
import currency.exchanger.dto.CurrencyExchangeResponse;
import currency.exchanger.error.ApplicationError;
import currency.exchanger.exception.CannotExchangeException;
import currency.exchanger.exception.CurrencyNotFoundException;
import currency.exchanger.service.CurrencyExchangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CurrencyControllerTest extends BaseControllerTest {

    @MockBean
    private CurrencyExchangeService currencyExchangeService;

    @Test
    void testExchangeCurrency() throws Exception {
        when(currencyExchangeService.exchangeCurrency(any()))
                .thenAnswer(invocation -> CurrencyExchangeMock.getExchangeResponse());

        MvcResult mvcResult = getMockMvc()
                .perform(
                        MockMvcRequestBuilders.post("/v1/currency/exchange")
                                .with(httpBasic("user", "pass"))
                                .content(mapper.writeValueAsString(CurrencyExchangeMock.getExchangeRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        CurrencyExchangeResponse exchangeResponse = mapper.readValue(responseString, CurrencyExchangeResponse.class);

        Assertions.assertNotNull(exchangeResponse);
        Assertions.assertFalse(exchangeResponse.getFromCurrency().isEmpty());
        Assertions.assertFalse(exchangeResponse.getToCurrency().isEmpty());
        Assertions.assertTrue(exchangeResponse.getToQuantity() > 0);
        Assertions.assertTrue(exchangeResponse.getToQuantity() > 0);
    }

    @Test
    void testExchangeCurrencyCurrencyNotFoundException() throws Exception {
        when(currencyExchangeService.exchangeCurrency(any()))
                .thenThrow(new CurrencyNotFoundException());

        MvcResult mvcResult = getMockMvc()
                .perform(
                        MockMvcRequestBuilders.post("/v1/currency/exchange")
                                .with(httpBasic("user", "pass"))
                                .content(mapper.writeValueAsString(CurrencyExchangeMock.getExchangeRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        assertRequestError(responseString, ApplicationError.NOT_FOUND_CURRENCY);
    }

    @Test
    void testExchangeCurrencyCannotExchangeException() throws Exception {
        when(currencyExchangeService.exchangeCurrency(any()))
                .thenThrow(new CannotExchangeException());

        MvcResult mvcResult = getMockMvc()
                .perform(
                        MockMvcRequestBuilders.post("/v1/currency/exchange")
                                .with(httpBasic("user", "pass"))
                                .content(mapper.writeValueAsString(CurrencyExchangeMock.getExchangeRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        assertRequestError(responseString, ApplicationError.EXCHANGE_NOT_POSSIBLE);
    }

}
