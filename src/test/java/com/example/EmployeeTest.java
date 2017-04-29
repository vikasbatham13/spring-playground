/*
package com.example;

import com.example.config.SecurityConfig;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.rest.EmployeesController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.Base64Utils;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
public class EmployeeTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void testManagerRole() throws Exception {
        Employee emp1 = new Employee();
        emp1.setManagerId(1L);
        emp1.setName("Test");
        emp1.setSalary(1000);
        BDDMockito.given(this.employeeRepository.findAll()).willReturn(Arrays.asList(emp1));
        RequestBuilder request = get("/admin/employees").with(user("boss").roles("ADMIN"));
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated().withRoles("ADMIN"));

    }


    @Test
    public void testAnyRole() throws Exception {
        Employee emp1 = new Employee();
        emp1.setManagerId(1L);
        emp1.setName("Test");
        emp1.setSalary(1000);
        BDDMockito.given(this.employeeRepository.findAll()).willReturn(Arrays.asList(emp1));
        RequestBuilder request = get("/admin/employees").with(anonymous());
        mockMvc.perform(request).andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    public void indexAllowsEmployeeUsers() throws Exception {
        Employee emp1 = new Employee();
        emp1.setManagerId(1L);
        emp1.setName("Test");
        emp1.setSalary(1000);
        BDDMockito.given(this.employeeRepository.findAll()).willReturn(Arrays.asList(emp1));
        RequestBuilder request = get("/employees").with(user("user").roles("EMPLOYEE"));
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void okResponseWithBasicAuthCredentialsForKnownUser() throws Exception {
        this.mockMvc
                .perform(get("/employees").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("employee:my-employee-password".getBytes())))
                .andExpect(status().isOk());
    }

  */
/*  @Test
    @WithMockUser(roles = "EMPLOYEE")
    public void adminUsersDisallowsEmployees() throws Exception {
        RequestBuilder request = get("/admin/employees");
        mockMvc.perform(request).andExpect(status().isForbidden());
    }*//*

}
*/
