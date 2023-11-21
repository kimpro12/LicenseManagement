package com.LicenseManagement.Service;

import com.LicenseManagement.Repository.LicenseRepository;
import com.LicenseManagement.Entity.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenseServiceImpl implements LicenseService {
    private LicenseRepository licenseRepository;
    @Autowired
    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }
    @Override
    public List<License> findAll() {
        return licenseRepository.findAll();
    }

    @Override
    public License save(License license) {
        return licenseRepository.save(license);
    }

    @Override
    public License findById(int id) {
        Optional<License> result = licenseRepository.findById(id);
        return result.orElse(null);
    }
    @Override
    public void deleteById(int id) {
        licenseRepository.deleteById(id);
    }
    @Override
    public void deleteAll() {
        licenseRepository.deleteAll();
        licenseRepository.resetAutoIncrement();
    }

    @Override
    public List<License> findByLicense(String license) {
        return licenseRepository.findByLicense(license);
    }
}
