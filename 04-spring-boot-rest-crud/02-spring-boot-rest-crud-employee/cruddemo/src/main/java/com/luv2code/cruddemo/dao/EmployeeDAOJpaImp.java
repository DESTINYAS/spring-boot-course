package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOJpaImp implements EmployeeDAO{

    // define field for EntityManager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImp(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

    //   Create a query
        TypedQuery<Employee> employeeTypedQuery=entityManager.createQuery("from Employee", Employee.class);

    //   execute query and get result
        List<Employee>employees=employeeTypedQuery.getResultList();

    //    return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
    //        get the employee
            Employee theEmployee = entityManager.find(Employee.class,theId);
    //        return the employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //use merge to either save or update employee depending on if id==0 or not
        Employee dbEmployee=entityManager.merge(theEmployee);
        //return employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        // findById
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //remove employee
        entityManager.remove(theEmployee);

    }
}
