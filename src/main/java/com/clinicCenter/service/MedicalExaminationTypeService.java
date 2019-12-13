package com.clinicCenter.service;

import com.clinicCenter.model.MedicalExaminationType;

import java.util.Collection;
import java.util.Set;


public interface MedicalExaminationTypeService {
    Collection<MedicalExaminationType> getAllMedicalExaminationTypes();

    Set<MedicalExaminationType> getAll();

    MedicalExaminationType getById(Long id);

    void removeById(Long id);

    int updateType(MedicalExaminationType type);

    void save(MedicalExaminationType type);
}
