package com.sekhar.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekhar.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
