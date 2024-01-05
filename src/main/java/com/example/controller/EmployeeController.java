package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public String showList(Model model) {
		List<Employee> employees = this.employeeService.findAllEmployee();
		model.addAttribute("employees", employees);
		return "employee/list";
	}

	@GetMapping("/find/{employeeId}")
	public String showEmployee(@PathVariable Integer employeeId, Model model) {
		Employee employee = this.employeeService.findEmployee(employeeId);
		model.addAttribute("employee", employee);
		return "employee/data";
	}

	@GetMapping("/searchByName/{name}")
	public String searchEmployee(@PathVariable String name, Model model) {
		List<Employee> employees = this.employeeService.findByName(name);
		model.addAttribute("employees", employees);
		return "employee/list";
	}

	@GetMapping("/create")
	public String addEmployee(@RequestParam("name") String name
			, @RequestParam("department") String department) {
		this.employeeService.insert(name, department);
		return "redirect:/employee/list";
	}

	@GetMapping("/update/{employeeId}")
	public String editEmployee(@PathVariable Integer employeeId
			, @RequestParam("name") String name
			, @RequestParam("department") String department) {
		this.employeeService.update(employeeId, name, department);
		return "redirect:/employee/list";
	}

	@GetMapping("/delete/{employeeId}")
	public String deleteEmployee(@PathVariable Integer employeeId) {
		this.employeeService.delete(employeeId);
		return "redirect:/employee/list";
	}
}
