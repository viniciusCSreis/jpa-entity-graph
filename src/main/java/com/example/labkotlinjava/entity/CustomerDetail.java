package com.example.labkotlinjava.entity;

import javax.persistence.*;

@Entity
@Table(name="customer_detail")
public class CustomerDetail {
    @Id
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerInfo customerInfo;

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
