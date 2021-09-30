package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
