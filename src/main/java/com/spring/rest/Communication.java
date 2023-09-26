package com.spring.rest;

import com.spring.rest.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_PORT = "8084";
   // private static final String URL = "http://localhost:" + URL_PORT + "/Spring_MVC_RestAPI/api/employees";
    private static final String URL = "http://localhost:" + URL_PORT + "/springBoot-rest/api/employees";

    public List<Employee> getAllEmployees() {

        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Employee>>() {
                });

        List<Employee> allEmps = responseEntity.getBody();

        return allEmps;
    }

    public Employee getEmployee(int empId) {

        Employee employee = restTemplate.getForObject(URL + "/" + empId, Employee.class);

        return employee;
    }

    public void saveEmployee(Employee employee) {
        int id = employee.getId();

        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);

            System.out.println("New Employee was created:");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.println("Employee was updated with ID: " + id);
        }
    }


    public void deleteEmployee(int empId) {
        restTemplate.delete(URL + "/" + empId);
        System.out.println("Employee was deleted with ID: " + empId);
    }

}
