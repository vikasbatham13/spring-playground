package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee,Long>{
    Employee findByUsername(String username);
}
