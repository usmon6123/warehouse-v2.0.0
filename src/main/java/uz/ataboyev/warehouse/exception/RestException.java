package uz.ataboyev.warehouse.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import uz.ataboyev.warehouse.payload.ErrorData;


import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException {

    private String userMsg;
    private HttpStatus status;
    private List<ErrorData> errors;

    public RestException(String userMsg, HttpStatus status) {
        super(userMsg);
        this.userMsg = userMsg;
        this.status = status;
    }

    private RestException(HttpStatus status, List<ErrorData> errors) {
        this.status = status;
        this.errors = errors;
    }

    public static RestException restThrow(String userMsg, HttpStatus httpStatus) {
        return new RestException(userMsg, httpStatus);
    }

    public static RestException restThrow(String userMsg) {
        return new RestException(userMsg, HttpStatus.BAD_REQUEST);
    }

    public static RestException restThrow(List<ErrorData> errors, HttpStatus status) {
        return new RestException(status, errors);
    }


    public static RestException notFound(String message) {
        return new RestException(message, HttpStatus.NOT_FOUND);
    }

    public static RestException alreadyExists(String message) {
        return new RestException(message, HttpStatus.CONFLICT);
    }

    public static RestException attackResponse() {
        return new RestException("ATTACK_RESPONSE", HttpStatus.BAD_REQUEST);
    }

    public static RestException forbidden() {
        return new RestException("FORBIDDEN", HttpStatus.FORBIDDEN);
    }

    public static RestException exception(String message, HttpStatus status) {
        return new RestException(message, status);
    }
}
