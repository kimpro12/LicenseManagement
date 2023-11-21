package com.LicenseManagement.ExceptionHandle;

public class LicenseNotFoundException extends RuntimeException {
    public LicenseNotFoundException(String message) {
        super(message);
    }
}
