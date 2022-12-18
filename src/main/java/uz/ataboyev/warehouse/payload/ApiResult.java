package uz.ataboyev.warehouse.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//null qiymatlarni frontga yubormaydi
public class ApiResult<T> {
    private Boolean success = false;
    private String message;
    private T data;
    private List<ErrorData> errors;


    //RESPONSE WITH BOOLEAN (SUCCESS OR FAIL)
    private ApiResult(Boolean success) {
        this.success = success;
    }

    private ApiResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    //SUCCESS RESPONSE WITH DATA
    public ApiResult(T data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    //SUCCESS RESPONSE WITH DATA AND MESSAGE
    public ApiResult(T data, Boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    //SUCCESS RESPONSE WITH MESSAGE
    public ApiResult(String message) {
        this.message = message;
        this.success = Boolean.TRUE;
    }

    //ERROR RESPONSE WITH MESSAGE AND ERROR CODE
    public ApiResult(String errorMsg, Integer errorCode) {
        this.success = false;
        this.errors = Collections.singletonList(new uz.ataboyev.warehouse.payload.ErrorData(errorMsg, errorCode));
    }

    //ERROR RESPONSE WITH ERROR DATA LIST
    public ApiResult(List<uz.ataboyev.warehouse.payload.ErrorData> errors) {
        this.success = false;
        this.errors = errors;
    }



    /**
     * Data bor va success bulsa datani qaytaradi. Aks holda berilgan Exceptionga otadi.
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exception) throws X {
        if (data != null && success) {
            return data;
        } else {
            throw exception.get();
        }
    }

    public <X extends Throwable> void noSuccessThrow(Supplier<? extends X> exception) throws X {
        if (!success) throw exception.get();
    }

    public static <E> ApiResult<E> successResponse(E data) {

        return new ApiResult<>(data, true);
    }

    public static <E> ApiResult<E> successResponse(E data, String message) {
        return new ApiResult<>(data,
                true,
                message
        );
    }

    public static <E> ApiResult<E> successResponse() {
        return new ApiResult<>(true);
    }

    public static ApiResult<?> errorResponse(String message) {
        return new ApiResult<>(false,message);
    }

    public static ApiResult<String> successResponse(String message) {
        return new ApiResult<>(message);
    }


}
