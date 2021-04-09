package vn.daoanhthanh.SE2_Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.daoanhthanh.SE2_Hospital.model.patient.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
