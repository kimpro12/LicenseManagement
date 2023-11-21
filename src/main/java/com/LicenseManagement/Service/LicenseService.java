package com.LicenseManagement.Service;

import com.LicenseManagement.Entity.License;

import java.util.List;

public interface LicenseService {
    List<License> findAll();
    License save(License license);
    License findById(int id);
    void deleteById(int id);
    void deleteAll();
    List<License> findByLicense(String license);
}
