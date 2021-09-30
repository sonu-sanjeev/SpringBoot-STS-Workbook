package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	/**
	 * Using JPA method proxy.
	 */
	
	List<Student> findByFirstName(String firstName);
	
	// Here the order of the arguments matters.
	List<Student> findByFirstNameAndLastName(String firstName, String lastName); 
	
	List<Student> findByFirstNameOrLastName(String firstName, String lastName);
	
	//In Query - To apply OR condition with multiple values
	List<Student> findByFirstNameIn(List<String> firstNames);
	
	//Like Query
	List<Student> findByFirstNameContains(String key);
	
	//Starts With
	List<Student> findByFirstNameStartsWith(String key);
	
	
	/** 
	 * Using JPQL queries. 
	 */
	
	@Query("From Student where lastName = :lastname and firstName = :firstName")
	Student getByLastNameAndFirstName(String firstName, @Param("lastname") String lastName);
	
	// If your query is doing any modification, then we need to add '@Modifying' and '@Transactional' annotations.
	@Modifying
	@Transactional
	@Query("Update Student set email = :email where id = :id")
	Integer updateEmailWithJpql(String email, Integer id);
	
	@Modifying
	@Transactional
	@Query("Delete From Student where email = :email")
	Integer deleteByEmail(String email);
} 
