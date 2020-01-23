package com.clinicCenter.repository;

import com.clinicCenter.model.MedicalExaminationRoom;
import com.clinicCenter.model.OperationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface OperationRoomRepository extends JpaRepository<OperationRoom,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE OperationRoom r SET r.name = :name, r.number = :number WHERE r.id = :id")
    int updateRoom(Long id, String name, Integer number);

    @Query(value = "SELECT * from db.operation_room oper where lower(oper.name) like %:name% and oper.number = :number ", nativeQuery = true)
    Set<OperationRoom> getSearched(String name, Integer number);

    @Query(value = "SELECT * from db.operation_room oper where lower(oper.name) like %:name% ", nativeQuery = true)
    Set<OperationRoom> getSearchedByName(String name);
}