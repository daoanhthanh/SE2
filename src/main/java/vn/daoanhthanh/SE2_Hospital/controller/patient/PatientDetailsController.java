package vn.daoanhthanh.SE2_Hospital.controller.patient;

import org.springframework.web.bind.annotation.RestController;

import vn.daoanhthanh.SE2_Hospital.Repository.patient.PatientDetailsRepository;
import vn.daoanhthanh.SE2_Hospital.error.Patient.PatientDetailsNotFoundException;
// import vn.daoanhthanh.SE2_Hospital.model.patient.Patient;
import vn.daoanhthanh.SE2_Hospital.model.patient.PatientDetails;

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
class PatientDetailsController {

  private final PatientDetailsRepository repository;

  PatientDetailsController(PatientDetailsRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/patientDetails")
  List<PatientDetails> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/patientDetails")
  PatientDetails newPatientDetails(@RequestBody PatientDetails newPatientDetails) {
    return repository.save(newPatientDetails);
  }

  // Single item
  
  @GetMapping("/patientDetails/{id}")
  PatientDetails one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new PatientDetailsNotFoundException(id));
  }

  @PutMapping("/patientDetails/{id}")
  PatientDetails replacePatientDetails(@RequestBody PatientDetails newPatientDetails, @PathVariable Long id) {
  
    return repository.findById(id)
      .map(PatientDetails -> {

        //TODO
        PatientDetails.setBloodtype(newPatientDetails.getBloodtype());
        PatientDetails.setHeigth(newPatientDetails.getHeigth());
        PatientDetails.setWeigth(newPatientDetails.getWeigth());
        return repository.save(PatientDetails);
      })
      .orElseGet(() -> {
        newPatientDetails.setId(id);
        return repository.save(newPatientDetails);
      });
  }

  @DeleteMapping("/patientDetails/{id}")
  void deletePatientDetails(@PathVariable Long id) {
    repository.deleteById(id);
  }
}