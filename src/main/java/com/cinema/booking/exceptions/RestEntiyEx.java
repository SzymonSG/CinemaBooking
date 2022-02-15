package com.cinema.booking.exceptions;

import com.cinema.booking.entities.ApiError;
import com.cinema.booking.entities.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
@ResponseStatus
public class RestEntiyEx extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MovieNotFoundException.class})
    ResponseEntity<ErrorMessage> movieNotFoundException(MovieNotFoundException exception,
                                                        WebRequest webRequest){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }


    @ExceptionHandler({AlreadyEnrolledMovieException.class})
    ResponseEntity<ErrorMessage> movieNotFoundException(AlreadyEnrolledMovieException exception,
                                                        WebRequest webRequest){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler({CinemaNotFoundException.class})
    ResponseEntity<ErrorMessage> movieNotFoundException(CinemaNotFoundException exception,
                                                        WebRequest webRequest){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler({PropertyMovieNotFoundException.class})
    ResponseEntity<ErrorMessage> movieNotFoundException(PropertyMovieNotFoundException exception,
                                                        WebRequest webRequest){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }


    // working with constarins value without @Valid
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<String>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    //Working with missMatchType also wothout value
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    //java guide working with Valid addnotaion
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
    }
        //Beadlung working with Valid addnotaion
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//
//        ApiError apiError =
//                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return handleExceptionInternal(
//                ex, apiError, headers, apiError.getStatus(), request);
//    }
}
