package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.one_to_one.Employee;
import com.codedecode.demo.service.EmployeeServiceInterface;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;
	
	@PostMapping("/save")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
		return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}
	
	/*
	 * @author: Nguyễn Thế Toàn 
	*/
//	@GetMapping("/all")
//	public ResponseEntity<?> getAllEmployees(){
//		List<Employee> listOfAllEmps = employeeServiceInterface.getAllEmployees();
//		if (listOfAllEmps.size() == 0) {
//			return new ResponseEntity<IllegalArgumentException>(HttpStatus.BAD_REQUEST);
//		}
//		
//		return new ResponseEntity<List<Employee>>(listOfAllEmps, HttpStatus.OK);
//	}
	
	@GetMapping("/employee/{empid}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("empid") Long empidL){
		System.err.println("empidL: " +empidL);
		Employee empRetrieved = employeeServiceInterface.getEmpById(empidL);
		System.out.println("empRetrieved: " + empRetrieved);
		return new ResponseEntity<Employee>(empRetrieved, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<Void> deleteEmpById(@PathVariable("empid") Long empidL){
		
		employeeServiceInterface.deleteEmpById(empidL);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
		return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}

	
	
}
