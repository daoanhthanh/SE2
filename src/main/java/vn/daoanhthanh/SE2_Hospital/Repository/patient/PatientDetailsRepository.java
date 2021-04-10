package vn.daoanhthanh.SE2_Hospital.Repository.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.daoanhthanh.SE2_Hospital.model.patient.PatientDetails;

public interface PatientDetailsRepository extends JpaRepository<PatientDetails,Long> {
    
}
