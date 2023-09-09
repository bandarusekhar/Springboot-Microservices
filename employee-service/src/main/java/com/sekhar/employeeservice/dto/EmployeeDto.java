package com.sekhar.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EmployeeDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String departmentCode;
}
