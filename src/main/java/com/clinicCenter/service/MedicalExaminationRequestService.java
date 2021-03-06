package com.clinicCenter.service;

import com.clinicCenter.model.MedicalExaminationRequest;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface MedicalExaminationRequestService {
    void sendRequest(Long typeId, Date date, Long clinicId, Long doctorId, Long patientId);

    Collection<MedicalExaminationRequest> getAllExaminationRequests(Long adminId);

    MedicalExaminationRequest getById(Long requestId);

    List<MedicalExaminationRequest> getAllExamsRequests();


}
