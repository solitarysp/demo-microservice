package com.lethanh98.service.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 03-09-2019 16:37  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@RestControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(new Date());
        exceptionResponse.setStatus(HttpStatus.FORBIDDEN.value());
        exceptionResponse.setError("Forbidden");
        exceptionResponse.setMessage("Access denied");
        exceptionResponse.setPath(request.getDescription(false).split("uri=")[1]);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthorizationServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException1(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(new Date());
        exceptionResponse.setStatus(HttpStatus.FORBIDDEN.value());
        exceptionResponse.setError("Forbidden");
        exceptionResponse.setMessage("Access denied");
        exceptionResponse.setPath(request.getDescription(false).split("uri=")[1]);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}
