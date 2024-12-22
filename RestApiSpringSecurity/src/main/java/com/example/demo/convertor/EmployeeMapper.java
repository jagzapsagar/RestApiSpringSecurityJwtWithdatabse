package com.example.demo.convertor;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;

@Service
public class EmployeeMapper {
	
	public EmployeeDTO ConvertToDTO(Employee emp) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(emp.getId());
		dto.setName(emp.getName());
		dto.setAddress(emp.getAddress());
		dto.setSalary(emp.getSalary());
		return dto;
		
	}
	
	public Employee convertToEnity(EmployeeDTO dto) {
		Employee emp = new Employee();
		emp.setId(dto.getId());
		emp.setName(dto.getName());
		emp.setAddress(dto.getAddress());
		emp.setSalary(dto.getSalary());
		return emp;
		
	}

}
