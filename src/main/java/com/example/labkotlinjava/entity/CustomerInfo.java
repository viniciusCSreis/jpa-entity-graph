package com.example.labkotlinjava.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer_info")
public class CustomerInfo {
    @Id
    private Long id;

    private String photo;

    @OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL)
    private List<OccupationRole> roles;

    @OneToOne
    private CustomerDetail customerDetail;

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<OccupationRole> getRoles() {
        return roles;
    }

    public void setRoles(List<OccupationRole> roles) {
        this.roles = roles;
    }
}
