package com.as.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ProblemDetail userNotFoundExcetion(UserNotFound unknownUser, WebRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, unknownUser.getDetail());
        problemDetail.setTitle(unknownUser.getTitle());
        if(unknownUser.getType()!=null){
            problemDetail.setType(URI.create(unknownUser.getType()));
        }
        return problemDetail;
    }
}
