package com.sekhar.departmentservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekhar.departmentservice.dto.DepartmentDto;
import com.sekhar.departmentservice.entity.Department;
import com.sekhar.departmentservice.exceptions.ResourceNotFoundException;
import com.sekhar.departmentservice.repository.DepartmentRepository;
import com.sekhar.departmentservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

		Department departmentEntity = modelMapper.map(departmentDto, Department.class);
		Department savedDepartmentEntity = departmentRepository.save(departmentEntity);
		DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartmentEntity, DepartmentDto.class);

		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {

		Department departmentEntity = departmentRepository.findByDepartmentCode(departmentCode);
		if(null==departmentEntity) {
			throw new ResourceNotFoundException("department", "id", departmentCode);
		}
		DepartmentDto departmentDto = modelMapper.map(departmentEntity, DepartmentDto.class);
		return departmentDto;
	}

}
