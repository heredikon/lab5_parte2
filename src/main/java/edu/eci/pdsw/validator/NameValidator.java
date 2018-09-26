package edu.eci.pdsw.validator;

import java.util.Optional;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.validator.ErrorType;

/**
 * Utility class to validate an employee's salary
 */
public class NameValidator implements EmployeeValidator {

   /**
    * NAME VALIDATOR
    * Debe contener al menos 1 nombre y 1 apellido.
    * La longitud debe ser entre 10 y 30 caracteres.
    * EQ1: name.length < 10 v name-legth > 30 invalid
    * EQ2: 10 <= name.length <= 30 valid
    * EQ3: name.split(" ").length != 2 (Si no hay nombre y apellido) invalido
    */
    public Optional<ErrorType> validate(Employee employee) {
    
        String name = employee.getName();
        
        if ((name.length() < 10) || (name.length() > 30)){
            return Optional.of(ErrorType.INVALID_LONG_NAME);
        }else if ((name.length() >= 10) && (name.length() <= 30)){
            //Si hay nombre y apellido
            System.out.println("Size: "+name.split(" ").length);
            if (name.split(" ").length != 2){
                return Optional.of(ErrorType.INVALID_FIRSTLAST_NAME);
            }else{
                return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
    }
}