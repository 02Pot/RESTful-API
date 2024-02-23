package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.CloudVendor;
import com.response.ResponseHandler;
import com.service.CloudVendorService;


@RestController
@RequestMapping("/cloudvendor")
public class CloudAPIController {
    
    CloudVendorService cloudVendorService;

    public CloudAPIController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        CloudVendor vendor = cloudVendorService.getCloudVendor(vendorId);
        return ResponseHandler.responseBuilder("Requested ID Details:",HttpStatus.OK,vendor);
        
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCloudVendorDetails(){
        List<CloudVendor> vendors = cloudVendorService.getAllCloudVendor();
        return ResponseHandler.responseBuilder("All Cloud Vendor", HttpStatus.OK, vendors);
    }

    @PostMapping
    public ResponseEntity<Object> createCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
        String message = cloudVendorService.createCloudVendor(cloudVendor);
        return ResponseHandler.responseBuilder(message, HttpStatus.CREATED,null);
    }

    @PutMapping
    public ResponseEntity<Object> updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
        String message = cloudVendorService.updateCloudVendor(cloudVendor);
        return ResponseHandler.responseBuilder(message, HttpStatus.OK,null);
    }

    @DeleteMapping("{vendorId}")
    public ResponseEntity<Object> deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId) {
        String message = cloudVendorService.deleteCloudVendor(vendorId);
        return ResponseHandler.responseBuilder(message,HttpStatus.OK,null);
    }
}

