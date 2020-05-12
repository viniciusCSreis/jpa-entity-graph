package com.example.labkotlinjava.repository;

import com.example.labkotlinjava.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo,Long> {
}
