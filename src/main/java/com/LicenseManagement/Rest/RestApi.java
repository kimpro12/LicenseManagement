package com.LicenseManagement.Rest;

import com.LicenseManagement.Entity.License;
import com.LicenseManagement.ExceptionHandle.LicenseNotFoundException;
import com.LicenseManagement.ExceptionHandle.RequestBodyNotFoundException;
import com.LicenseManagement.Service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class RestApi {
    private LicenseService licenseService;
    @Autowired
    public RestApi(LicenseService licenseService) {
        this.licenseService = licenseService;
    }
    @GetMapping("/licenses")
    public List<License> getAllLicense() {
        return licenseService.findAll();
    }
    @GetMapping("/licenses/{id}")
    public License getLicense(@PathVariable("id") int id) {
        License license = licenseService.findById(id);
        if (license == null) {
            throw new LicenseNotFoundException("License id not found");
        }
        return license;
    }
    @PostMapping("/licenses")
    public License addLicense(@RequestBody(required = false) License license) {
        if (license == null) {
            License ret = new License(generateRandomString(20));
            licenseService.save(ret);
            return ret;
        }
        license.setId(0);
        return licenseService.save(license);
    }
    @PutMapping("/licenses")
    public License updateLicense(@RequestBody(required = false) License license) {
        if (license == null) {
            throw new RequestBodyNotFoundException("Request Body is required");
        }
        return licenseService.save(license);
    }
    @DeleteMapping("/licenses")
    public String deleteAllLicense() {
        licenseService.deleteAll();
        return "Delete all licenses successfully";
    }
    @DeleteMapping("/licenses/{id}")
    public String deleteLicense(@PathVariable("id") int id) {
        License license = licenseService.findById(id);
        if (license == null) {
            throw new LicenseNotFoundException("License id not found to delete");
        }
        licenseService.deleteById(id);
        return "Delete license successfully";
    }
    @GetMapping("/licenses/verify")
    public List<License> checkLicense(@RequestParam(name = "license") String license) {
        return licenseService.findByLicense(license);
    }
    private String generateRandomString(int length) {
        // Define the characters that the random string will be made of
        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Create a StringBuilder to hold the result
        StringBuilder result = new StringBuilder(length);

        // Create an instance of the Random class
        Random random = new Random();

        // Append a random character from the characterSet to the result
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            result.append(characterSet.charAt(randomIndex));
        }

        // Convert the StringBuilder to a String and return it
        return result.toString();
    }
}
