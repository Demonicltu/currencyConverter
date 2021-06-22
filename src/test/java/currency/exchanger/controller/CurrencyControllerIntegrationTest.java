package currency.exchanger.controller;

import currency.exchanger.data.CurrencyExchangeMock;
import currency.exchanger.model.dto.CurrencyExchangeResponse;
import currency.exchanger.error.ApplicationError;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CurrencyControllerIntegrationTest extends BaseControllerTest {

    @Test
    void testExchangeCurrency() throws Exception {
        MvcResult mvcResult = getMockMvc()
                .perform(
                        MockMvcRequestBuilders.post("/v1/currency/exchange")
                                .with(httpBasic("user", "pass"))
                                .content(mapper.writeValueAsString(CurrencyExchangeMock.getExchangeRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(documentExchangeCurrency())
                .andDo(print())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        CurrencyExchangeResponse exchangeResponse = mapper.readValue(responseString, CurrencyExchangeResponse.class);

        assertNotNull(exchangeResponse);
        assertFalse(exchangeResponse.getFromCurrency().isEmpty());
        assertFalse(exchangeResponse.getToCurrency().isEmpty());
        assertTrue(exchangeResponse.getToQuantity() > 0);
        assertTrue(exchangeResponse.getToQuantity() > 0);
    }

    @Test
    void testExchangeCurrencySameCurrency() throws Exception {
        MvcResult mvcResult = getMockMvc()
                .perform(
                        MockMvcRequestBuilders.post("/v1/currency/exchange")
                                .with(httpBasic("user", "pass"))
                                .content(mapper.writeValueAsString(CurrencyExchangeMock.getExchangeRequestSameCurrency()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        assertRequestError(responseString, ApplicationError.EXCHANGE_NOT_POSSIBLE);
    }

    private RestDocumentationResultHandler documentExchangeCurrency() {
        return document(
                "exchangeCurrency",
                preprocessRequest(removeHeaders("Content-Length", "Host"), prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                        headerWithName("Authorization").description("Basic authentication header")
                ),
                requestFields(
                        fieldWithPath("fromCurrency").type(JsonFieldType.STRING).description("Currency of currently owned money"),
                        fieldWithPath("toCurrency").type(JsonFieldType.STRING).description("Exchange currency"),
                        fieldWithPath("fromQuantity").type(JsonFieldType.NUMBER).description("Quantity of currently owned money")
                ),
                responseFields(
                        fieldWithPath("fromCurrency").type(JsonFieldType.STRING).description("Currency of currently owned money"),
                        fieldWithPath("toCurrency").type(JsonFieldType.STRING).description("Exchange currency"),
                        fieldWithPath("fromQuantity").type(JsonFieldType.NUMBER).description("Quantity of currently owned money"),
                        fieldWithPath("toQuantity").type(JsonFieldType.NUMBER).description("Quantity of exchanged money")
                )
        );
    }

}
