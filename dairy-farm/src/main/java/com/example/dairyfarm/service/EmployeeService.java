package com.example.dairyfarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.Employee;
import com.example.dairyfarm.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = getEmployeeById(id);

        if (existing != null) {

            existing.setEmployeeName(employee.getEmployeeName());
            existing.setGender(employee.getGender());
            existing.setMobile(employee.getMobile());
            existing.setAddress(employee.getAddress());
            existing.setJoiningDate(employee.getJoiningDate());
            existing.setSalary(employee.getSalary());
            existing.setRole(employee.getRole());
            existing.setStatus(employee.getStatus());

            return employeeRepository.save(existing);
        }

        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
