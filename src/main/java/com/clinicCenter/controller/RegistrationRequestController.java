package com.clinicCenter.controller;

import com.clinicCenter.model.*;
import com.clinicCenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;
import java.util.function.LongFunction;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    @Autowired
    private MedicalRecordService medicalRecordService;

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
        System.out.println(registrationRequest.getEmail());
        MedicalRecord medicalRecord = new MedicalRecord();
        Patient patient = new Patient(registrationRequest.getEmail(),
                passwordEncoder.encode(registrationRequest.getPassword()),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getAddress(),
                registrationRequest.getCity(),
                registrationRequest.getCountry(),
                registrationRequest.getPhone(),
                registrationRequest.getSsn());
        registrationRequestService.delete(registrationRequest);
        List<Authority> auth = authService.findByName("ROLE_PATIENT");
        patient.setAuthorities(auth);
        patient.setMedicalRecord(medicalRecord);
        patient.setPasswordChanged(false);
        medicalRecordService.save(medicalRecord);
        userService.save(patient);
        Patient p = (Patient) userService.getByEmail(registrationRequest.getEmail());
        emailService.sendMailToUser(patient.getEmail(), "http://localhost:4200/activateUser/" + p.getId(), "Automated mail : Activate account");
    }

    @DeleteMapping(value = "/registrationRequests/removeRequest/{id}/{message}")
    public void removeById(@PathVariable Long id, @PathVariable String message) throws InterruptedException {
        System.out.println(message);
        RegistrationRequest req = registrationRequestService.getById(id);
        emailService.sendMailToUser(req.getEmail(), message, "Automated mail : Your account has been declined");
        registrationRequestService.removeById(id);
    }
}
