package com.sekhar.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sekhar.employeeservice.dto.DepartmentDto;

@FeignClient(name="DEPARTMENT-SERVICE")
public interface APIClient {

	@GetMapping("api/departments/{departmentCode}")
	DepartmentDto getDepartment(@PathVariable String departmentCode);
}
