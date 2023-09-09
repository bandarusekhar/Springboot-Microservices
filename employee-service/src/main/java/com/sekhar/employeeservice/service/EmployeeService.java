package com.sekhar.employeeservice.service;

import com.sekhar.employeeservice.dto.APIResponse;
import com.sekhar.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	APIResponse getEmployeeById(Long employeeId);
}
