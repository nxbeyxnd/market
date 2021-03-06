package com.alexeysherkhonov.core.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@ControllerAdvice
@RequestMapping("/error")
public class ExceptionController {

    @ExceptionHandler
    private ResponseEntity<?> handleResourceNotFindException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        ExceptionMarket error = new ExceptionMarket(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
