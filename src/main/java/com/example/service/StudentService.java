package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.request.InQueryRequest;
import com.example.request.StudentRequest;
import com.example.request.UpdateStudentRequest;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAll() {
		return studentRepository.findAll(); // Get all records.
	}
	
	public List<Student> create(StudentRequest studentRequest) {
		Student student = new Student(studentRequest);
		studentRepository.save(student); //Create a new record in student table and return the created record.
		return studentRepository.findAll();
	}
	
	public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
		Student student = studentRepository.findById(updateStudentRequest.getId()).get();
		
		if (updateStudentRequest.getFirstName() != null) {
			student.setFirstName(updateStudentRequest.getFirstName());
			studentRepository.save(student);
		}	
		
		return student;
	}
	
	public String deleteStudent(int id) {
		studentRepository.deleteById(id);
		return "Student deleted successfully";
	}
	
	public List<Student> getStudentByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName);
	}
	
	public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest) {
		return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
	}
	
	// select * from student limit pageNo offset (pageNo-1) * pageSize
	public List<Student> getAllWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize); // This is a zero based indexing. Hence we take 'pageNo-1'
		return studentRepository.findAll(pageable).getContent();
	}
	
	//select * from student order by first_name, last_name asc (or desc)
	public List<Student> getAllStudentWithSorting() {
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName");
		return studentRepository.findAll(sort);
	}
	
	//Like query
	//select * from student where first_name like '%key%'
	public List<Student> like(String key) {
		return studentRepository.findByFirstNameContains(key);
	}
	
	//StartsWith query
	//select * from student where first_name like 'key%'
	public List<Student> startsWith(String key) {
		return studentRepository.findByFirstNameStartsWith(key);
	}
	
}






