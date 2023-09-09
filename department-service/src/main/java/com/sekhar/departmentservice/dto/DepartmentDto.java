package com.sekhar.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DepartmentDto {
	private Long id;
	private String departmentName;
	private String departmentDescription;
	private String departmentCode;
}
