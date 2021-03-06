package com.clinicCenter.repository;

import com.clinicCenter.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Clinic c SET c.clinicAverageRating = :averageRating, c.timesRated = :timesRated WHERE c.id = :id")
    void updateRating(Long id, Double averageRating, Integer timesRated);


    @Query(value = "SELECT * FROM db.clinic c Where c.name = :clinicName", nativeQuery = true)
    Clinic findByName(String clinicName);

    @Query(value = "SELECT * FROM db.clinic c WHERE c.id = (SELECT u.clinic_id FROM db.users u WHERE u.id = :id)", nativeQuery = true)
    Clinic getAdminsClinic(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE db.clinic SET name = :namee, description = :description, city = :city, address = :address, clinic_average_rating = :clinicAverageRating where id = :id", nativeQuery = true)
    void updateClinic(Long id, String namee, String description, String city, String address, Double clinicAverageRating);

    @Query(value = "SELECT c.id, c.name, c.description, c.city, c.address, c.clinic_average_rating, c.times_rated FROM clinic c where c.id in (SELECT d.clinic_id FROM users d where d.id in (SELECT dbe.doctor_id FROM doctor_examination_types dbe where dbe.type_id = :type_idd))", nativeQuery = true)
    Collection<Clinic> getSearchedClinics(@Param("type_idd") Long type_idd);

    @Query(value = "SELECT c.id, c.name, c.description, c.city, c.address, c.clinic_average_rating, c.times_rated FROM clinic c where c.clinic_average_rating >= :clinicRating and c.id in (SELECT d.clinic_id FROM users d where d.id in (SELECT dbe.doctor_id FROM doctor_examination_types dbe where dbe.type_id = :type_idd))", nativeQuery = true)
    Collection<Clinic> getSearchedClinicsTwo(@Param("type_idd") Long type_idd, @Param("clinicRating") Double clinicRating);

    @Query(value = "SELECT c.id, c.name, c.description, c.city, c.address, c.clinic_average_rating, c.times_rated FROM clinic c where c.name LIKE %:clinicName% and c.id in (SELECT d.clinic_id FROM users d where d.id in (SELECT dbe.doctor_id FROM doctor_examination_types dbe where dbe.type_id = :type_idd))", nativeQuery = true)
    Collection<Clinic> getSearchedClinicsThree(@Param("type_idd") Long type_idd, @Param("clinicName") String clinicName);

    @Query(value = "SELECT c.id, c.name, c.description, c.city, c.address, c.clinic_average_rating, c.times_rated FROM clinic c where c.clinic_average_rating >= :clinicRating and c.name LIKE %:clinicName% and c.id in (SELECT d.clinic_id FROM users d where d.id in (SELECT dbe.doctor_id FROM doctor_examination_types dbe where dbe.type_id = :type_idd))", nativeQuery = true)
    Collection<Clinic> getSearchedClinicsFour(@Param("type_idd") Long type_idd, @Param("clinicName") String clinicName, @Param("clinicRating") Double clinicRating);

}
