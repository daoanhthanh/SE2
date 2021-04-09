package vn.daoanhthanh.SE2_Hospital.error.Patient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PatientNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(PatientNotFoundException ex) {
        return ex.getMessage();
    }
}
