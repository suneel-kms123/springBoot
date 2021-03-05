package com.programspring.SpringBoot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionAdvice {
    
    @ResponseBody
    @ExceptionHandler(MethodNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String methodNotFoundExceptionHandler(MethodNotFoundException message) {
        return message.getMessage();
    }

}
