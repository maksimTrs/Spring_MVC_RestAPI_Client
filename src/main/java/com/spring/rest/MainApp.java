package com.spring.rest;

import com.spring.rest.conf.AppConfig;
import com.spring.rest.entities.Employee;
import org.junit.Assert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigInteger;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

            Communication communication = context.getBean("communication", Communication.class);

            List<Employee> allEmployees = communication.getAllEmployees();
            allEmployees.forEach(System.out::println);
            System.out.println("========================================================================");

            Employee employeeById = communication.getEmployee(2);
            System.out.println(employeeById);
            Assert.assertEquals(employeeById.getName(), "Oleg");
            System.out.println("========================================================================");

            Employee newEmp = new Employee("User1", "User11", "HR", new BigInteger("77777777"));
            communication.saveEmployee(newEmp);

            newEmp.setId(22);
            newEmp.setName("User1_UP_UP");
            communication.saveEmployee(newEmp);
            System.out.println(newEmp);
            System.out.println("========================================================================");

            communication.deleteEmployee(22);
        }
    }
}
