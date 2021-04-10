package vn.daoanhthanh.SE2_Hospital.controller.patient;

import org.springframework.web.bind.annotation.RestController;

import vn.daoanhthanh.SE2_Hospital.Repository.patient.PatientRepository;
import vn.daoanhthanh.SE2_Hospital.error.Patient.PatientNotFoundException;
import vn.daoanhthanh.SE2_Hospital.model.patient.Patient;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hanu_hospital")
class PatientController {

  private final PatientRepository repository;

  PatientController(PatientRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/patients")
  List<Patient> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/patients")
  Patient newPatient(@RequestBody Patient newPatient) {
    return repository.save(newPatient);
  }

  // Single item
  
  @GetMapping("/patients/{id}")
  Patient one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new PatientNotFoundException(id));
  }

  @PutMapping("/patients/{id}")
  Patient replacePatient(@RequestBody Patient newPatient, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(Patient -> {

        //TODO
        Patient.setName(newPatient.getName());
        Patient.setDob(newPatient.getDob());
        Patient.setAddress(newPatient.getAddress());
        Patient.setPhoneNumber(newPatient.getPhoneNumber());
        return repository.save(Patient);
      })
      .orElseGet(() -> {
        newPatient.setId(id);
        return repository.save(newPatient);
      });
  }

  @DeleteMapping("/patients/{id}")
  void deletePatient(@PathVariable Long id) {
    repository.deleteById(id);
  }
}