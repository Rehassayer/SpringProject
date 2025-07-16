package com.appsoft.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.appsoft.springdemo.model.Department;
import com.appsoft.springdemo.service.DepartmentService;
import com.appsoft.springdemo.utils.DepartmentExcelView;
import com.appsoft.springdemo.utils.DepartmentPdfView;

import jakarta.servlet.http.HttpSession;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/department")
	public String getDepartmentForm(HttpSession session) {
		
		if (session.getAttribute("activeuser") ==null) {
			
			return "LoginForm";
		}
		return "DepartmentForm";
	}
	
	@PostMapping("/department")
	public String postDepartment(@ModelAttribute Department dept) {
		
		deptService.addDeparment(dept);
		
		return "DepartmentForm";
	}
	
	@GetMapping("/departmentlist")
	public String getAll(Model model) {
		
		model.addAttribute("dlist",deptService.getAllDepartment());
		
		
		return "DepartmentListForm";
	}
					

	@GetMapping("/dept/delete")
	public String deleteDept(@RequestParam("id")int id) {
		
		deptService.deleteDepartment(id);
		
		return "redirect:/departmentlist";
	}
			
	@GetMapping("/dept/edit")
	public String editDept(@RequestParam("id") int id, Model model) {
		model.addAttribute("dObject",deptService.getDepartmentById(id));
		
		
		return "DepartmentEditForm" ;
	}
	
	@PostMapping("/dept/update")
	public String updateDept(@ModelAttribute Department dept) {
		
		deptService.updateDepartment(dept);
		return "redirect:/departmentlist";
	}
	
	@GetMapping("/dept/excel")
	public ModelAndView excel() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("dList",deptService.getAllDepartment());
		mv.setView(new DepartmentExcelView());
		return mv;
		
	}
	
	@GetMapping("/dept/pdf")
	public ModelAndView pdf() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("dList",deptService.getAllDepartment());
		mv.setView(new DepartmentPdfView());
		return mv;
		
	}
	
	}
	

