package edu.eci.pdsw.validator;

import java.util.Optional;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;
import edu.eci.pdsw.validator.ErrorType;

/**
 * Utility class to validate an employee's salary
 */
public class SocialSecurityValidator implements EmployeeValidator {

   /**
    * SOCIAL SECURITY VALIDATOR
    * Afiliación al Sisben es solo válida para salarios menores a $1.500
    * Afiliación a EPS es solo válida para salarios menores a $10.000
    * Afiliación a Prepagada es válida para los casos adicionales
    * EQ1: salary<1.500 (SISBEN)
    * EQ2: salary<10.000 (EPS)
    * EQ3: salary>=10000 (PREPAID)
    */
    public Optional<ErrorType> validate(Employee employee) {
    
        long salary = employee.getSalary();
        SocialSecurityType socSecType = employee.getSocialSecurityType();
        
        if (socSecType == SocialSecurityType.SISBEN){
            if (salary >= 1500){
                return Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION);
            }else{
                return Optional.empty();
            }
            
        }else if (socSecType == SocialSecurityType.EPS){
            if (salary >= 10000){
                return Optional.of(ErrorType.INVALID_EPS_AFFILIATION);
            }else{
                return Optional.empty();
            }
            
        }else if (socSecType == SocialSecurityType.PREPAID){
            if (salary < 10000){
                return Optional.of(ErrorType.INVALID_PREPAID_AFFILIATION);
            }else{
                return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
    }
}