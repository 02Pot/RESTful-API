package com.repository.restDemo;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.model.CloudVendor;
import com.repository.CloudVendorRepository;

@DataJpaTest
public class CloudVendorRepositoryTest {
    
    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp(){
        cloudVendor = new CloudVendor("1","Raul",
        "Raul Address",
        "Raul Phone Number");
        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown(){
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }

    //Success Test Case
    @Test
    void testFindByVendorName_Found(){
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Raul");
        assertThat(cloudVendorList).isNotEmpty();

        CloudVendor foundCloudVendor = cloudVendorList.get(0);
        assertEquals("1", foundCloudVendor.getVendorId());
        assertEquals("Raul", foundCloudVendor.getVendorName());
        assertEquals("Raul Address", foundCloudVendor.getVendorAddress());
        assertEquals("Raul Phone Number", foundCloudVendor.getVendorPhoneNumber());
    }

    //Failure Test Case
    @Test
    void testFindByVendorName_NotFound(){
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("ASDSd");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }
}
