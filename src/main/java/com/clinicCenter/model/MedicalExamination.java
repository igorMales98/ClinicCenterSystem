package com.clinicCenter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class MedicalExamination {

    public MedicalExamination() {}


    public MedicalExamination(Date date, Double price, Double duration, Double discount, MedicalExaminationRoom room,
                              Doctor doctor, Patient patient, Clinic clinic, MedicalExaminationType type, Boolean predefined) {
        this.date = date;
        this.price = price;
        this.duration = duration;
        this.discount = discount;
        this.clinic = clinic;
        this.medicalExaminationRoom = room;
        this.doctor = doctor;
        this.patient = patient;
        this.type = type;
        this.confirmed = false;
        this.predefined = predefined;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column
    private Double duration;

    @Column
    private Double discount;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "met_id")
    private MedicalExaminationType type;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "mer_id")
    private MedicalExaminationRoom medicalExaminationRoom;

    @Column
    private Boolean confirmed = false;

    @Column
    private Boolean predefined = false;

    @Column
    private Boolean doctorRated = false;

    @Column Boolean clinicRated = false;

    @Column Boolean finished = false;

}
