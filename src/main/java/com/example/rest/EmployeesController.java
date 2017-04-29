package com.example.rest;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EmployeesController {

    @Autowired
    EmployeeRepository employeeRepository;


    @JsonView(View.Summary.class)
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }


    @GetMapping(value = "/admin/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Employee> getEmployeeDetail() throws IOException {
        return this.employeeRepository.findAll();
    }

}
