package currency.exchanger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import currency.exchanger.error.ApplicationError;
import currency.exchanger.error.RequestError;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    protected void assertRequestError(String response, ApplicationError applicationError) throws JsonProcessingException {
        RequestError error = mapper.readValue(response, RequestError.class);

        Assertions.assertEquals(applicationError.getErrorName(), error.getErrorName());
        Assertions.assertEquals(applicationError.getHttpStatus().toString(), error.getHttpStatus());
    }

}
