package com.sekhar.departmentservice.service;

import com.sekhar.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

	DepartmentDto saveDepartment(DepartmentDto departmentDto);

	DepartmentDto getDepartmentByCode(String departmentCode);
}
