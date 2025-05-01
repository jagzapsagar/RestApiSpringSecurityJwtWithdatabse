package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.jwt.AuthenticationRequest;
import com.example.demo.jwt.AuthenticationResponse;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.services.EmployeeService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			// Authenticate the user using their credentials
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			// throw new Exception("Incorrect username or password", e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED) // Return 401 status for incorrect credentials
					.body(new String("Incorrect username or password"));

		}

		// Load the user details
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		// Generate the JWT token
		final String jwt = jwtUtil.generateToken(userDetails);

		// Return the token in the response
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/getall")
	public List<EmployeeDTO> getAll() {
		return employeeService.getAll();

	}

	@GetMapping("/get/{id}")
	// @PreAuthorize("hasAuthority('USER')") //Alternative for RequestMather in
	// config file Method level Authrization
	public ResponseEntity<EmployeeDTO> getById(@PathVariable Integer id) {
		try {
			EmployeeDTO empd = employeeService.getById(id);
			return new ResponseEntity<>(empd, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/post")
	public ResponseEntity<Object> save(@Valid @RequestBody Employee emp) {
		return employeeService.save(emp);

	}

	@PutMapping("/put")
	public ResponseEntity<Object> update(@RequestBody Employee emp) {
		return employeeService.update(emp);

	}
	
	@DeleteMapping
	public ResponseEntity<Object> delete(@PathVariable int id){
		String message = employeeService.deleteById(id);
		return ResponseEntity.ok().body(message); // HTTP 200 OK with success message
		
	}

}
