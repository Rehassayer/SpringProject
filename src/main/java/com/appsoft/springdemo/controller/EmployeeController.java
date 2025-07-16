package com.appsoft.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.springdemo.model.Employee;
import com.appsoft.springdemo.service.DepartmentService;
import com.appsoft.springdemo.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;

	@GetMapping("/employee")
	public String getEmployee(Model model, HttpSession session) {
		
       if (session.getAttribute("activeuser") ==null) {
			
			return "LoginForm";
		}
		model.addAttribute("dList",deptService.getAllDepartment());
		return "EmployeeForm";
	}
	
	@PostMapping("/employee")
	public String postEmployee(@ModelAttribute Employee emp) {
		
		empService.addEmp(emp);
		return "redirect:/employee";
	}
	
	@GetMapping("/employeelist")
	public String getAll(Model model) {
		model.addAttribute("empList",empService.getAllEmps());
		return "EmployeeList";
	}
	
	@GetMapping("/emp/edit")
	public String edit(@RequestParam("id") long id,Model model) {
		
		model.addAttribute("eModel",empService.getEmpById(id));
		return "EditEmployeeForm";
	}
	
	@PostMapping("/emp/update")
	public String update(@ModelAttribute Employee emp) {
		
		empService.updateEmp(emp);
		
		return "redirect:/employeelist";
	}
	
	@GetMapping("emp/delete")
	public String delete(@RequestParam("id") long id) {
		
		empService.deleteEmp(id);
		return "redirect:/employeelist";
	}
}
