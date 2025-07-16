package com.appsoft.springdemo.service;

import java.util.List;

import com.appsoft.springdemo.model.Department;

public interface DepartmentService {

	void addDeparment(Department dept);
	void deleteDepartment(int id);
	void updateDepartment(Department dept);
	Department getDepartmentById(int id);
	
	List<Department> getAllDepartment();
}
