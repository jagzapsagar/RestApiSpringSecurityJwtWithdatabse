package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "RestApiSpringSecurity")
public class Employee {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@NotNull(message = "Name cannot be null")
	@Column( name= "NAME")
	private String name;
	
	@NotNull(message = "Address cannot be null")
	@Column( name= "ADDRESS")
	private String address;
	
	@NotNull(message = "Salary cannot be null")
	@Column( name= "SALARY")
	private int salary;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name, String address, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", salary=" + salary + "]";
	}
	
	

}
