package com.appsoft.springdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsoft.springdemo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value= "Select * From Employee_tbl e JOIN department_tbl d WHERE d.dept_Name = :deptName",nativeQuery = true)
	List<Employee> findByDepartmentName(@Param("deptName") String deptName); 
	
}
