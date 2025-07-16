package com.appsoft.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appsoft.springdemo.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>{
	
	

}
