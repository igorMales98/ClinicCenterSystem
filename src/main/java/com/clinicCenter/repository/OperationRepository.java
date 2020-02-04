package com.clinicCenter.repository;

import com.clinicCenter.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query(value = "SELECT * FROM db.operation mo WHERE mo.doctor_id = :id", nativeQuery = true)
    List<Operation> getAllByDoctorId(Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Override
    Operation save(Operation operation);
}
