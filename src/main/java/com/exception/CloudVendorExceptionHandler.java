package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CloudVendorExceptionHandler {
    
    @ExceptionHandler(value = {CloudVendorNotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException(CloudVendorNotFoundException cloudVendorNotFoundException){

        CloudVendorException cloudVendorException = new CloudVendorException(
            cloudVendorNotFoundException.getMessage(),
            cloudVendorNotFoundException.getCause(),
            HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(cloudVendorException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CloudVendorAlreadyExistException.class})
    public ResponseEntity<Object> handleCloudVendorAlreadyExistException(CloudVendorAlreadyExistException cloudVendorAlreadyExistException){

        CloudVendorException cloudVendorException = new CloudVendorException(
            cloudVendorAlreadyExistException.getMessage(),
            cloudVendorAlreadyExistException.getCause(),
            HttpStatus.CONFLICT);

        return new ResponseEntity<>(cloudVendorException, HttpStatus.CONFLICT);
    }


}
