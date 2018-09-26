package edu.eci.pdsw.validator;

import java.util.Optional;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.validator.ErrorType;

/**
 * Utility class to validate an employee's salary
 */
public class SalaryValidator implements EmployeeValidator {

   /**
    * SALARY VALIDATOR
    * El rango salarial debe estar entre $100 y $50.000
    * EQ1: salary < 100
    * EQ2: 100 <= salary <= 50.000
    * EQ3: salary > 50.000
    */
    public Optional<ErrorType> validate(Employee employee) {
        
        long salary = employee.getSalary();
        
        if (salary < 100){
            return Optional.of(ErrorType.INVALID_SALARY);
        }else if ((salary >= 100) && (salary <= 50000)){
            return Optional.empty();
        }else if (salary > 50000){
            return Optional.of(ErrorType.INVALID_SALARY);
        }else{
            return Optional.empty();
        }
    }
}
