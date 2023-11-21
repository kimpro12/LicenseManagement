package com.LicenseManagement.ExceptionHandle;

public class RequestBodyNotFoundException extends RuntimeException {
    public RequestBodyNotFoundException(String message) {
        super(message);
    }
}
