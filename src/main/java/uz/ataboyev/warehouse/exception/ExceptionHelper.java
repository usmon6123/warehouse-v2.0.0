package uz.ataboyev.warehouse.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ErrorData;


import java.lang.reflect.InvocationTargetException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHelper {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        List<ErrorData> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(new ErrorData(errorMessage, HttpStatus.BAD_REQUEST.value()));
        });
        return new ResponseEntity<>(new ApiResult<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TypeMismatchException.class})
    public ResponseEntity<?> handleException(TypeMismatchException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<?> handleException(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ServletRequestBindingException.class})
    public ResponseEntity<?> handleException(ServletRequestBindingException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<?> handleException(MissingServletRequestPartException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<?> handleException(BindException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<?> handleException(AccessDeniedException ex) {
        return new ResponseEntity<>(
                "Bu yo'lga kirishga huquq yo'q",
                HttpStatus.FORBIDDEN);
    }



    @ExceptionHandler(value = {MissingPathVariableException.class})
    public ResponseEntity<?> handleException(MissingPathVariableException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<?> handleException(NoHandlerFoundException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND);
    }


    //METHOD XATO BO'LSA
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> handleException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(
                new ApiResult<>("Method Not Allowed Userning bu yo'lga kirishga permissioni yoq", 405),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<?> handleException(HttpMediaTypeNotAcceptableException ex) {
        return new ResponseEntity<>(
                new ApiResult<>("No acceptable", 406),
                HttpStatus.NOT_ACCEPTABLE);
    }


    //METHOD XATO BO'LSA
    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<?> handleException(HttpMediaTypeNotSupportedException ex) {
        return new ResponseEntity<>(
                new ApiResult<>("Method error", 415),
                HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(value = {ConversionNotSupportedException.class})
    public ResponseEntity<?> handleException(ConversionNotSupportedException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<?> handleException(NullPointerException ex) {
        return new ResponseEntity<>(
                new ApiResult<>("NullPointerException -> "+ex.getMessage(), 500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {InvocationTargetException.class})
    public ResponseEntity<?> handleException(InvocationTargetException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {HttpMessageNotWritableException.class})
    public ResponseEntity<?> handleException(HttpMessageNotWritableException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                new ApiResult<>("INTERNAL_SERVER_ERROR", 500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> handleException(RuntimeException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {AsyncRequestTimeoutException.class})
    public ResponseEntity<?> handleException(AsyncRequestTimeoutException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), 503),
                HttpStatus.SERVICE_UNAVAILABLE);
    }


    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<?> handleException(RestException ex) {
        return new ResponseEntity<>(new ApiResult<>(ex.getMessage(), ex.getStatus().value()), ex.getStatus());
    }

}
