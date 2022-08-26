package uz.jl.trelloapprest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.jl.trelloapprest.exceptions.GenericNotFoundException;
import uz.jl.trelloapprest.response.ApiErrorResponse;
import uz.jl.trelloapprest.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/17:14 (Friday)
 */

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(GenericNotFoundException.class)
    public ApiResponse<ApiErrorResponse> handle404(GenericNotFoundException e, HttpServletRequest request) {
        return new ApiResponse<>(ApiErrorResponse.builder()
                .friendlyMessage(e.getMessage())
                .developerMessage(e.getLocalizedMessage())
                .requestPath(request.getRequestURL().toString())
                .build(),
                e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        String requestURI = request.getRequestURI();
        return new ResponseEntity<>(ApiErrorResponse
                .builder()
                .friendlyMessage("Invalid Params Provided")
                .errorFields(errors)
                .requestPath(requestURI)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
