package com.clinicCenter.controller;

import com.clinicCenter.model.Medicament;
import com.clinicCenter.model.Patient;
import com.clinicCenter.model.RegistrationRequest;
import com.clinicCenter.service.RegistrationRequestService;
import com.clinicCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Set;
import java.util.function.LongFunction;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @Autowired
    private UserService userService;

    @GetMapping("/registrationRequests")
    public Set<RegistrationRequest> getAll(){
        Set<RegistrationRequest> requests = registrationRequestService.getAll();
        return  requests;
    }

    @GetMapping(value = "/registrationRequest/{id}")
    public RegistrationRequest getById(@PathVariable Long id){
       RegistrationRequest rr = registrationRequestService.getById(id);
       return rr;
    }

    @PostMapping(value = "/registrationRequests/acceptRequest")
    public void accept(@RequestBody RegistrationRequest registrationRequest){
        System.out.println(registrationRequest.getFirstName());
        Patient patient = new Patient(registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getAddress(),
                registrationRequest.getCity(),
                registrationRequest.getCountry(),
                registrationRequest.getPhone(),
                registrationRequest.getSsn());
        registrationRequestService.delete(registrationRequest);
        userService.save(patient);
    }
}
