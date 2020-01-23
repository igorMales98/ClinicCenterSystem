package com.clinicCenter.service;

import com.clinicCenter.model.MedicalExaminationRoom;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface MedicalExaminationRoomService {
    List<MedicalExaminationRoom> getAll();

    void removeById(Long id);

    MedicalExaminationRoom getById(Long id);

    int updateRoom(MedicalExaminationRoom room);

    void save(MedicalExaminationRoom room);

    Set<MedicalExaminationRoom> getRooms(String name, Integer number);

    Set<MedicalExaminationRoom> getRoomsName(String name);

    Set<MedicalExaminationRoom> getClinicRooms(Long clinicAdminId);

    List<MedicalExaminationRoom> getAvailableRooms(Long id, Date date);
}
