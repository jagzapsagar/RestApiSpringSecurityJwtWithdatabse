package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;

@SpringBootTest
public class EntityTest {
	
	private static Employee emp;
	
	
	@BeforeAll
    public static void setup() {
        System.out.println("----------- Before All ----");
        emp = new Employee();
    }
	
	@AfterAll
    public static void AfterAllTest() {
        System.out.println("----------- After All ----");
        emp = new Employee();
    }
	
	@BeforeEach
	public void BeforEachTest() {
		System.out.println("--- Before Each----");
	}
	
	@AfterEach
	@Disabled  // To disbled Test Case
	public void AfterEachTest() {
		System.out.println("--- After Each----");
	}

    @Test
    @DisplayName("Custom name for test case")
    public void testSettersAndGetters() {
        // Testing the setters
        emp.setId(2);
        emp.setName("Test Name");

        // Testing the getters
        assertEquals(2, emp.getId());
        assertEquals("Test Name", emp.getName());
    }
    
    @Test
    public void testSalary() {
        // Testing the setters
        emp.setSalary(22000);

        // Testing the getters
        assertEquals(23000, emp.getSalary(), "---Salary getters setters failed---");
        //Assertions.assertEquals(null, null);
       // assertEquals("Test Name2", emp.getName());
    }
    
    @Test
    public void testn() {
        // Testing the setters
        emp.setSalary(22000);

        // Testing the getters
        Assertions.assertEquals(30,30);
        Assertions.assertTrue(true);
        //Assertions.assertEquals(null, null);,
       // assertEquals("Test Name2", emp.getName());
    }
}

