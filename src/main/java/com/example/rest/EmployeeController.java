/*
package com.example.rest;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.rest.model.FinalResponse;
import com.example.rest.model.OMDBResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/admin/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Employee> getEmployeeDetail() throws IOException {
        return this.employeeRepository.findAll();
    }
}
*/
