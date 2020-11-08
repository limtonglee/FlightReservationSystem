/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.statelesss.EmployeeSessionBeanLocal;
import entity.Employee;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.EmployeeAccessRight;
import util.exception.EmployeeUsernameExistException;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zhiliangwang
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean
{

    @PersistenceContext(unitName = "FlightReservationSystem-ejbPU")
    private EntityManager em;
    

    @EJB
    private EmployeeSessionBeanLocal employeeSessionBeanLocal;

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PostConstruct
    public void postConstruct()
    {
        if(em.find(Employee.class, 1l) == null)
        {
            initializeEmployeeDate();
        }
    }
    
    private void initializeEmployeeDate()
    {
        try
        {
            employeeSessionBeanLocal.createEmployee(new Employee("System", "Admin", "admin", "password", EmployeeAccessRight.ADMIN));
            employeeSessionBeanLocal.createEmployee(new Employee("Fleet", "Manager", "fleetmanager", "password", EmployeeAccessRight.FLEETMANAGER));
            employeeSessionBeanLocal.createEmployee(new Employee("Route", "Planner", "routemanager", "password", EmployeeAccessRight.ROUTEPLANNER));
            employeeSessionBeanLocal.createEmployee(new Employee("Sales", "Manager", "salesmanager", "password", EmployeeAccessRight.SALESMANAGER));
            employeeSessionBeanLocal.createEmployee(new Employee("Schedule", "Manager", "schedulemanager", "password", EmployeeAccessRight.SCHEDULEMANAGER));
        }
        catch(EmployeeUsernameExistException | UnknownPersistenceException | InputDataValidationException ex)
        {
            ex.printStackTrace();;
        }
    }

}
