package com.example.demo.services;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;

public interface EmployeeService {
	List<EmployeeDTO> getAll();
	EmployeeDTO getById(int id);
	ResponseEntity<Object> save(Employee emp);
	ResponseEntity<Object> update(Employee emp);
	

}
