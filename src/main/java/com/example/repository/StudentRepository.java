package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	List<Student> findByFirstName(String firstName);
	
	// Here the order of the arguments matters.
	List<Student> findByFirstNameAndLastName(String firstName, String lastName); 
	
	List<Student> findByFirstNameOrLastName(String firstName, String lastName);
	
	//In Query - To apply OR condition with multiple values
	List<Student> findByFirstNameIn(List<String> firstNames);
}
