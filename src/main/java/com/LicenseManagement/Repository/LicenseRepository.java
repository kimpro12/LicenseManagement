package com.LicenseManagement.Repository;

import com.LicenseManagement.Entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LicenseRepository extends JpaRepository<License, Integer> {
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE license AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    List<License> findByLicense(String license);
}
