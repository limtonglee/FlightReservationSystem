/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.statelesss;

import entity.Employee;
import javax.ejb.Local;
import util.exception.EmployeeNotFoundException;
import util.exception.EmployeeUsernameExistException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zhiliangwang
 */
@Local
public interface EmployeeSessionBeanLocal
{

    public Long createEmployee(Employee newEmployee) throws EmployeeUsernameExistException, UnknownPersistenceException, InputDataValidationException;
    
    public Employee employeeLogin(String username, String password) throws InvalidLoginCredentialException;

    public Employee retrieveEmployeeByUsername(String username) throws EmployeeNotFoundException;
    
}
