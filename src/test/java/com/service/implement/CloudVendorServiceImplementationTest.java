package com.service.implement;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.exception.CloudVendorNotFoundException;
import com.model.CloudVendor;
import com.repository.CloudVendorRepository;
import com.service.CloudVendorService;

public class CloudVendorServiceImplementationTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImplementation(cloudVendorRepository);
        cloudVendor = new CloudVendor("1","Raul","Raul Address","Raul Phone Number");
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Create Success");
    }

    @Test
    void testDeleteCloudVendor() {
        String vendorId = "nonExistentId";
        when(cloudVendorRepository.existsById(vendorId)).thenReturn(false);

        Throwable exception = assertThrows(CloudVendorNotFoundException.class, () -> {
            cloudVendorService.deleteCloudVendor(vendorId);
        });

        assertThat(exception.getMessage()).isEqualTo("ID does not Exist");
    }

    @Test
    void testGetAllCloudVendor() {
        when(cloudVendorRepository.findAll()).thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));

        assertThat(cloudVendorService.getAllCloudVendor().get(0).getVendorPhoneNumber()).isEqualTo(cloudVendor.getVendorPhoneNumber());
    }

    @Test
    void testGetCloudVendor() {
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.getCloudVendor("1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
    }

    @Test
    void testUpdateCloudVendor() {
        when(cloudVendorRepository.existsById(cloudVendor.getVendorId())).thenReturn(false);

        Throwable exception = assertThrows(CloudVendorNotFoundException.class, () ->{
            cloudVendorService.updateCloudVendor(cloudVendor);
        });

        assertThat(exception.getMessage()).isEqualTo("Cloud Vendor not found");
    }
}
