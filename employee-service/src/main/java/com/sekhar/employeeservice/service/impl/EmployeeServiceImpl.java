package com.sekhar.employeeservice.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekhar.employeeservice.dto.APIResponse;
import com.sekhar.employeeservice.dto.DepartmentDto;
import com.sekhar.employeeservice.dto.EmployeeDto;
import com.sekhar.employeeservice.entity.Employee;
import com.sekhar.employeeservice.exceptions.ResourceNotFoundException;
import com.sekhar.employeeservice.repository.EmployeeRepository;
import com.sekhar.employeeservice.service.APIClient;
import com.sekhar.employeeservice.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

		Employee employeeEntity = modelMapper.map(employeeDto, Employee.class);

		Employee savedEntity = employeeRepository.save(employeeEntity);

		EmployeeDto savedEmployeeDto = modelMapper.map(savedEntity, EmployeeDto.class);

		return savedEmployeeDto;
	}

	@CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponse getEmployeeById(Long employeeId) {

		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("employee", "id", employeeId));
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		
		DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
		APIResponse apiResponse = new APIResponse();
		apiResponse.setDepartmentDto(departmentDto);
		apiResponse.setEmployeeDto(employeeDto);
		return apiResponse;
	}
	
	public APIResponse getDefaultDepartment(Long employeeId, Exception exception) {

		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("employee", "id", employeeId));
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		
		//DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
		
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("cse");
		departmentDto.setDepartmentDescription("cse is a good branch");
		departmentDto.setDepartmentCode("CS001");
		
		APIResponse apiResponse = new APIResponse();
		apiResponse.setDepartmentDto(departmentDto);
		apiResponse.setEmployeeDto(employeeDto);
		return apiResponse;
	}

}
