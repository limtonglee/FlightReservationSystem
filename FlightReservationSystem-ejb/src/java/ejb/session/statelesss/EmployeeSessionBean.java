/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.statelesss;

import entity.Employee;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.EmployeeNotFoundException;
import util.exception.EmployeeUsernameExistException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zhiliangwang
 */
@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanRemote, EmployeeSessionBeanLocal
{

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    
    public EmployeeSessionBean()
    {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Long createEmployee(Employee newEmployee) throws EmployeeUsernameExistException, UnknownPersistenceException, InputDataValidationException
    {
        Set<ConstraintViolation<Employee>>constraintViolations = validator.validate(newEmployee);
        
        if(constraintViolations.isEmpty())
        {
            try
            {
                em.persist(newEmployee);
                em.flush();
        
                return newEmployee.getEmployeeId();
            }
            catch(PersistenceException ex)
            {
                if(ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException"))
                {
                    if(ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException"))
                    {
                        throw new EmployeeUsernameExistException("Employee Username already existing in system, please user another username.");
                    }
                    else
                    {
                        throw new UnknownPersistenceException(ex.getMessage());
                    }
                }
                else
                {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }

    }
    
    @Override
    public Employee employeeLogin(String username, String password) throws InvalidLoginCredentialException
    {   
        try
        {
            Employee employee = retrieveEmployeeByUsername(username);
            
            if(employee.getPassword().equals(password))
            {
                return employee;
            }
            else
            {
                throw new InvalidLoginCredentialException("Username does not exist or invalid passowrd!");
            }
        }
        catch(EmployeeNotFoundException ex)
        {
            throw new InvalidLoginCredentialException("Username does not exist or invalid passowrd!");
        }
    }
    
    @Override
    public Employee retrieveEmployeeByUsername(String username) throws EmployeeNotFoundException
    {
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.username = :inUsername")
                .setParameter("inUsername", username);
        
        try
        {
            return (Employee)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new EmployeeNotFoundException("Employee Username: " + username + " does not exist!");
        }
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Employee>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
}
