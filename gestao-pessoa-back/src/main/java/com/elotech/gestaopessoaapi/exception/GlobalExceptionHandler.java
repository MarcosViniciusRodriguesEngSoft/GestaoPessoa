package com.elotech.gestaopessoaapi.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> badRequestExceptionHandler(BadRequestException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        
        final ExceptionResponse errorDetails = new ExceptionResponse(ex.getMessage(), getListCauses(ex, request));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    private List<String> getListCauses(Exception ex, WebRequest request) {
        final List<String> causes = new ArrayList<>();
        causes.add(ex.getMessage());
        addCause(ex, causes);
        causes.add(request.getDescription(false));
        return causes;
    }

    private void addCause(Throwable throwable, List<String> list) {
        if (throwable.getCause() != null) {
            list.add(throwable.getCause().getMessage());
            addCause(throwable.getCause(), list);
        }
    }
}