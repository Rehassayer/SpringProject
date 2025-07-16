package com.appsoft.springdemo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.springdemo.model.Department;
import com.appsoft.springdemo.repository.DepartmentRepo;
import com.appsoft.springdemo.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepo deptRepo;
	
	@Override
	public void addDeparment(Department dept) {

		deptRepo.save(dept);
		
		
	}

	@Override
	public void deleteDepartment(int id) {

		deptRepo.deleteById(id);
		
	}

	@Override
	public void updateDepartment(Department dept) {

		deptRepo.save(dept);
		
	}

	@Override
	public List<Department> getAllDepartment() {

		return deptRepo.findAll();
	}

	@Override
	public Department getDepartmentById(int id) {

		
		
		return deptRepo.findById(id).get();
	}

	
}
