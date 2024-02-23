package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.CloudVendor;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, String>{
}
