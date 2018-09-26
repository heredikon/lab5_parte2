package edu.eci.pdsw.validator;

import java.util.Optional;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.validator.ErrorType;

/**
 * Utility class to validate an employee's salary
 */
public class IdValidator implements EmployeeValidator {

   /**
    * ID VALIDATOR
    * Debe ser un numerico entre 1.000 y 100.000
    * EQ1: id < 1000
    * EQ2: 1000 <= id <= 100.000
    * EQ3: id > 100.000
    */
    public Optional<ErrorType> validate(Employee employee) {
    
        int id = employee.getPersonId();
        
        if (id < 1000){
            return Optional.of(ErrorType.INVALID_ID);
        }else if ((id >= 1000) && (id <= 100000)){
            return Optional.empty();
        }else if (id > 100000){
            return Optional.of(ErrorType.INVALID_ID);
        }else{
            return Optional.empty();
        }
    }
}