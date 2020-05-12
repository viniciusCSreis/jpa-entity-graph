package com.example.labkotlinjava.repository;

import com.example.labkotlinjava.entity.CustomerDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long> {

    @EntityGraph(attributePaths = "customerInfo.roles")
    @Query(value = "from CustomerDetail")
        // A native SQL query cannot use EntityGraphs
    List<CustomerDetail> search();
}
