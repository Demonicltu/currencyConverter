package currency.exchanger.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ActuatorIntegrationTest extends BaseControllerTest {

    @Test
    void testHealthCheck() throws Exception {
        MvcResult mvcResult = getMockMvc()
                .perform(
                        MockMvcRequestBuilders.get("/actuator/health")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(documentHealthCheck())
                .andDo(print())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        assertFalse(responseString.isEmpty());
    }

    private RestDocumentationResultHandler documentHealthCheck() {
        return document(
                "healthCheck",
                preprocessRequest(removeHeaders("Content-Length", "Host"), prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("status").type(JsonFieldType.STRING).description("Application health status")
                )
        );
    }

}
