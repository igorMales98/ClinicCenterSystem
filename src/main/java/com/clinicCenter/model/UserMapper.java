package com.clinicCenter.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserMapper {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phone;
    private Long ssn;
}