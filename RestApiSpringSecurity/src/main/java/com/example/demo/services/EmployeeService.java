package com.example.demo.services;
import java.util.List;
import com.example.demo.dto.EmployeeDTO;

public interface EmployeeService {
	List<EmployeeDTO> getAll();
	
	EmployeeDTO getById(int id);

}
