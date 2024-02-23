package com.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exception.CloudVendorAlreadyExistException;
import com.exception.CloudVendorNotFoundException;
import com.model.CloudVendor;
import com.repository.CloudVendorRepository;
import com.service.CloudVendorService;

@Service
public class CloudVendorServiceImplementation implements CloudVendorService{

    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImplementation(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        if(cloudVendorRepository.existsById(cloudVendor.getVendorId())){
            throw new CloudVendorAlreadyExistException("Cloud Vendor Already Exist");
        }
        
        cloudVendorRepository.save(cloudVendor);
        return "Create Success";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        if(!cloudVendorRepository.existsById(cloudVendor.getVendorId())){
            throw new CloudVendorNotFoundException("Cloud Vendor not found");
        }

        cloudVendorRepository.save(cloudVendor);
        return "Update Success";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        if(cloudVendorRepository.findById(cloudVendorId).isEmpty()){
            throw new CloudVendorNotFoundException("ID does not Exist");
        }
        cloudVendorRepository.deleteById(cloudVendorId);
        return "Delete Success";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        if(cloudVendorRepository.findById(cloudVendorId).isEmpty()){
            throw new CloudVendorNotFoundException("Requested ID Not Exist");
        }
        return cloudVendorRepository.findById(cloudVendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendor() {
        if(cloudVendorRepository.findAll().isEmpty()){
            throw new CloudVendorNotFoundException("List is Empty");
        }
        return cloudVendorRepository.findAll();
    }

}
