package com.jobhub2024.bankingservice.config;

import com.jobhub2024.bankingservice.exception.BadRequestException;
import com.jobhub2024.bankingservice.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@AllArgsConstructor
@RestControllerAdvice
public class RestExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "La solicitud tiene unos errores de validación.");

        Set<String> errors = new HashSet<>();
        List<FieldError> fieldErrors = ex.getFieldErrors();

        for (FieldError fe : fieldErrors) {
            String message = messageSource.getMessage(fe, Locale.getDefault().getDefault());
            errors.add(message);
        }
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResorceNotFoundException(ResourceNotFoundException ex){
        return ProblemDetail.
                forStatusAndDetail(HttpStatus.NOT_FOUND,"El recurso no ha sido encontrado");
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}

}
