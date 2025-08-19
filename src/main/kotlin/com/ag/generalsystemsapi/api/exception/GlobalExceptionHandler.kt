package com.ag.generalsystemsapi.api.exception

import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Date


@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    // handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        exception: ResourceNotFoundException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            Date(),
            exception.message?:"Unknown Error",
            webRequest.getDescription(false),
            ExceptionUtils.getStackTrace(exception)
        )
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    // Global exceptions
    @ExceptionHandler(Exception::class)
    fun handleGlobalException(
        exception: Exception,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            Date(),
            exception.message?:"Unknown Error",
            webRequest.getDescription(false),
            ExceptionUtils.getStackTrace(exception),
        )
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    //    @ExceptionHandler(MethodArgumentNotValidException.class)
    //    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
    //                                                                        WebRequest webRequest){
    //        Map<String, String> errors = new HashMap<>();
    //        exception.getBindingResult().getAllErrors().forEach((error) ->{
    //            String fieldName = ((FieldError)error).getField();
    //            String message = error.getDefaultMessage();
    //            errors.put(fieldName, message);
    //        });
    //        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    //    }
    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(
        exception: AccessDeniedException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            Date(),
            exception.message?:"Unknown Error",
            webRequest.getDescription(false),
            ExceptionUtils.getStackTrace(exception),
        )
        return ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED)
    }
}