package com.codedecode.demo.service;

import java.util.List;

import com.codedecode.demo.entity.one_to_one.Employee;

public interface EmployeeServiceInterface {

	public Employee addEmployee(Employee employee);

	public Employee getEmpById(Long empidL);

	public void deleteEmpById(Long empidL);

	List<Employee> getAllEmployees();

}
