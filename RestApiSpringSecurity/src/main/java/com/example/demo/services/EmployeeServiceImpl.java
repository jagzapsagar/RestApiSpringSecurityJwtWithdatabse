package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.convertor.EmployeeMapper;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<EmployeeDTO> getAll() {
		// TODO Auto-generated method stub
		List<Employee> list = employeeRepo.findAll();
		List<EmployeeDTO> DtoList = list.stream().map(e -> employeeMapper.ConvertToDTO(e)).collect(Collectors.toList());
		return DtoList;
	}

	@Override
	public EmployeeDTO getById(int id) {
		Employee emp = employeeRepo.findById(id).orElseThrow( () -> new UserNotFoundException("User not Found from service"));
		EmployeeDTO dto = employeeMapper.ConvertToDTO(emp);
		return dto;
	}

	@Override
	public ResponseEntity<Object> save(Employee emp) {
		// Check if the employee object has an ID
				if (emp.getId() != 0) {
					// If ID is provided, check if it already exists in the database
					Optional<Employee> existingEmployee = employeeRepo.findById(emp.getId());
					if (existingEmployee.isPresent()) {
						// If the ID already exists, return a conflict response with a custom error
						// message
						Map<String, String> errorResponse = new HashMap<>();
						errorResponse.put("error", "Employee with ID " + emp.getId() + " already exists.");
						return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
					}
				}

				// Save the employee (either with a provided or auto-generated ID)
				Employee savedEmployee = employeeRepo.save(emp);

				// Return the saved employee details
				return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	@Override
	public ResponseEntity<Object> update(Employee emp) {
		// Check if the employee object has a valid ID
				if (emp.getId() != 0) {
					// Check if the employee with the given ID exists in the database
					Optional<Employee> existingEmployee = employeeRepo.findById(emp.getId());
					if (existingEmployee.isPresent()) {
						// Get the existing employee and update fields
						Employee old = existingEmployee.get();
						old.setName(emp.getName());
						old.setSalary(emp.getSalary());
						old.setAddress(emp.getAddress());

						// Save the updated employee back to the database
						Employee updatedEmployee = employeeRepo.save(old);

						// Return the updated employee and status 200 OK
						return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
					} else {
						// Return 404 Not Found if employee with the provided ID does not exist
						Map<String, String> errorResponse = new HashMap<>();
						errorResponse.put("error", "Employee with ID " + emp.getId() + " not found.");
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
					}
				}

				// Return a bad request if ID is 0 or not provided
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("error", "Employee ID must be provided for update.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

}
