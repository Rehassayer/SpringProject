package com.appsoft.springdemo.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.appsoft.springdemo.model.Employee;
import com.appsoft.springdemo.model.Product;
import com.appsoft.springdemo.repository.ProductRepository;
import com.appsoft.springdemo.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/api/emp/list")
	public List<Employee> getEmps(){
		
		return empService.getAllEmps();
	}
	
	@PostMapping("/api/emp/add")
	public String add(@RequestBody Employee emp) {
		
		empService.addEmp(emp);
		return "added success";
	}
	@GetMapping("/api/emp/{id}")
	public Employee getOne(@PathVariable("id") long id) {
	
		return empService.getEmpById(id);
	}
	
	@DeleteMapping("/api/emp/delete/{id}")
	public String delete() {
		
		return "delete sucess";
	}
	@PutMapping("/api/emp/update")
	public String update(@RequestBody Employee emp) {
		
		empService.updateEmp(emp);
		return"update sucess";
	}
	
	@GetMapping("/api/emp/j2o")
	public String jsonToObject() {
		
		RestTemplate temp = new RestTemplate();
		Employee emp = temp.getForObject("http://localhost:9090/api/emp/2", Employee.class);
		
		
		return "FirstNAme : "+emp.getFname();
	}
	
	@GetMapping("/api/emp/ja2oa")
	public String jsonArrayToObjectArray() {
		
		RestTemplate temp = new RestTemplate();
		Employee[] emps = temp.getForObject("http://localhost:9090/api/emp/list", Employee[].class);
		
		return"Name : "+emps[0].getFname()+" "+emps[0].getLname() ;
	}
	
	@GetMapping("/api/emp/products")
	public String loadProdducts() {
		
		RestTemplate temp = new RestTemplate();
		
		Product[] prodList = temp.getForObject("https://fakestoreapi.com/products",Product[].class);
		
		prodRepo.saveAll(Arrays.asList(prodList));
		return "success";
	}
}
