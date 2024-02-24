package com.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.model.CloudVendor;
import com.service.CloudVendorService;


@WebMvcTest(CloudAPIController.class)
public class CloudAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CloudVendorService cloudVendorService;
    CloudVendor cloudVendorOne;
    CloudVendor cloudVendorTwo;
    List<CloudVendor> cloudVendorList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        cloudVendorOne = new CloudVendor("1","Raul","Raul Address","Raul Phone Number");
        cloudVendorTwo = new CloudVendor("Jordan","Jordan Address","Jordan Address","Jordan Phone Number");
        cloudVendorList.add(cloudVendorOne);
        cloudVendorList.add(cloudVendorTwo);
    }
    @AfterEach
    void tearDown(){
        cloudVendorList.clear();
    }
    @Test
    void testCreateCloudVendorDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(cloudVendorOne);

        when(cloudVendorService.createCloudVendor(cloudVendorOne))
        .thenReturn("Success");

        this.mockMvc.perform(post("/cloudvendor")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andDo(print())
        .andExpect(status().isCreated());
    }

    @Test
    void testDeleteCloudVendorDetails() throws Exception {
        when(cloudVendorService.deleteCloudVendor("1"))
        .thenReturn("Success");

        this.mockMvc.perform(delete("/cloudvendor/1"))
        .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("1")).
        thenReturn(cloudVendorOne);

        this.mockMvc.perform(get("/cloudvendor/1"))
        .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetCloudVendorDetails() throws Exception{
        when(cloudVendorService.getCloudVendor("1"))
        .thenReturn(cloudVendorOne);

        this.mockMvc.perform(get("/cloudvendor/1"))
        .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateCloudVendorDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(cloudVendorOne);

        when(cloudVendorService.updateCloudVendor(cloudVendorOne))
        .thenReturn("Success");

        this.mockMvc.perform(put("/cloudvendor")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andDo(print())
        .andExpect(status().isOk());
    }
}
