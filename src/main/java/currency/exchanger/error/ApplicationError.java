package currency.exchanger.error;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public enum ApplicationError {

    SYS_ERR("System error", INTERNAL_SERVER_ERROR),
    NOT_FOUND_CURRENCY("Not found currency", BAD_REQUEST),
    EXCHANGE_NOT_POSSIBLE("Exchange is not possible", BAD_REQUEST);

    private final String errorName;
    private final String message;
    private final HttpStatus httpStatus;

    ApplicationError(String message, HttpStatus httpStatus) {
        this.errorName = this.name();
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
