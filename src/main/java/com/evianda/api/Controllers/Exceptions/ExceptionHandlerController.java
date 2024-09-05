package com.evianda.api.Controllers.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@ControllerAdvice
public class ExceptionHandlerController {
    /*
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> invalidAuthenticationException(InvalidAuthenticationException e){
        StandardError standardError = new StandardError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
    }

     */
}
