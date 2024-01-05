package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> findAllEmployee() {
		return this.employeeRepository.findAll();
	}

	public Employee findEmployee(Integer employeeId) {
		Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
		Employee employee = optionalEmployee.get();
		return employee;
	}

	public List<Employee> findByName(String name) {
		return this.employeeRepository.findByName(name);
	}

	public Employee insert(String name, String department) {
		Employee employee = new Employee();
		employee.setName(name);
		employee.setDepartment(department);
		return this.employeeRepository.save(employee);
	}

	public Employee update(Integer employeeId, String name, String department) {
		Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
		Employee employee = optionalEmployee.get();
		employee.setName(name);
		employee.setDepartment(department);
		return this.employeeRepository.save(employee);
	}

	public void delete(Integer id) {
		this.employeeRepository.deleteById(id);
	}
}
