package com.clinicCenter.service;

import com.clinicCenter.model.Patient;
import com.clinicCenter.model.User;
import com.clinicCenter.model.UserMapper;

import java.util.Set;

public interface UserService {

    User getById(Long id);

    User getByEmail(String email);

    int updateUser(UserMapper user);

    void save(Patient patient);

    Set<String> getAllAdmins();

    void activateUser(Long id);
}


