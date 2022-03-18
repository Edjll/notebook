package ru.sstu.notepad.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerImpl {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ErrorResponse> errorResponses = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.errorMessages = objectError.getDefaultMessage();
                    return errorResponse;
                }
        ).collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponses);
    }

    @Data
    private class ErrorResponse {
        private String errorMessages;
    }

}
