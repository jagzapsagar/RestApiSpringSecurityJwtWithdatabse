package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.convertor.EmployeeMapper;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.UserRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<EmployeeDTO> getAll() {
		// TODO Auto-generated method stub
		List<Employee> list = userRepo.findAll();
		List<EmployeeDTO> DtoList = list.stream().map(e -> employeeMapper.ConvertToDTO(e)).collect(Collectors.toList());
		return DtoList;
	}

	@Override
	public EmployeeDTO getById(int id) {
		Employee emp = userRepo.findById(id).orElseThrow( () -> new UserNotFoundException("User not Found from service"));
		EmployeeDTO dto = employeeMapper.ConvertToDTO(emp);
		return dto;
	}

}
