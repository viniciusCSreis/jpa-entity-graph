package com.example.labkotlinjava.controller;

import com.example.labkotlinjava.controller.response.CustomerSearchResponse;
import com.example.labkotlinjava.entity.CustomerDetail;
import com.example.labkotlinjava.entity.OccupationRole;
import com.example.labkotlinjava.repository.CustomerDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private final CustomerDetailRepository customerDetailRepository;

    @Autowired
    public CustomerController(CustomerDetailRepository customerDetailRepository) {
        this.customerDetailRepository = customerDetailRepository;
    }

    @GetMapping("customers/search")
    List<CustomerSearchResponse> search() {
        return customerDetailRepository
                .search()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("customers/find-all")
    List<CustomerSearchResponse> findAll() {
        return customerDetailRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CustomerSearchResponse toResponse(CustomerDetail customerDetail) {
        List<String> roles = customerDetail
                .getCustomerInfo()
                .getRoles()
                .stream()
                .map(OccupationRole::getName)
                .collect(Collectors.toList());

        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        customerSearchResponse.setId(customerDetail.getId());
        customerSearchResponse.setName(customerDetail.getName());
        customerSearchResponse.setPhoto(customerDetail.getCustomerInfo().getPhoto());
        customerSearchResponse.setRoles(roles);
        return customerSearchResponse;
    }
}
