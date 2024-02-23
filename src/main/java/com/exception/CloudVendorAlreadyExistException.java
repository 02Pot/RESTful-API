package com.exception;

public class CloudVendorAlreadyExistException extends RuntimeException {
    
    public CloudVendorAlreadyExistException(String message){
        super(message);
    }

}
