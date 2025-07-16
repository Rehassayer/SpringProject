package com.appsoft.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsoft.springdemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String un, String pw);
	
	//@Query(value ="select * from user_tbl where username = :un and password = :pw",nativeQuery = true) //using sql queries
	
	@Query("from User where username = :un and password = :pw") //using jpa query
	User checkUser(@Param("un") String un,@Param("pw") String pw);
}
