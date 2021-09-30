package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.request.InQueryRequest;
import com.example.request.StudentRequest;
import com.example.request.UpdateStudentRequest;
import com.example.service.StudentService;
import org.springframework.http.HttpStatus;

@RestController //Combination of @Controller and @Responsebody annotation
@RequestMapping("/api/student/")
public class StudentController {
	
	@Value("${app.name:Default Name}")
	String appName;
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("getAll")
	public List<Student> getAllStudents() {
		return studentService.getAll();
	}

	@PostMapping("create") 
	public List<Student> create(@Valid @RequestBody StudentRequest studentRequest) {
		return studentService.create(studentRequest);
	}
	
	@PutMapping("update")
	public Student updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
		return studentService.updateStudent(updateStudentRequest);
	}
	
	@DeleteMapping("delete")
	public String deleteStudent(@RequestParam int id) {
		return studentService.deleteStudent(id);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteStudentByPath(@PathVariable int id) {
		return studentService.deleteStudent(id);
	}
	
	@GetMapping("getStudentByFirstName/{firstName}")
	public List<Student> getStudentByFirstName(@PathVariable String firstName) {
		return studentService.getStudentByFirstName(firstName);
	}
	
	@GetMapping("getByFirstNameIn")
	public List<Student> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
		return studentService.getByFirstNameIn(inQueryRequest);
	}
	
	@GetMapping("getAllWithPagination")
	public List<Student> getAllWithPagination(@RequestParam int pageNo, @RequestParam int pageSize) {
		return studentService.getAllWithPagination(pageNo, pageSize);
	}
	
	@GetMapping("getAllWithSorting")
	public List<Student> getAllStudentWithSorting() {
		return studentService.getAllStudentWithSorting();
	}
	
	@GetMapping("like/{key}")
	public List<Student> like(@PathVariable String key) {
		return studentService.like(key);
	}
	
	@GetMapping("startsWith/{key}")
	public List<Student> startsWith(@PathVariable String key) {
		return studentService.startsWith(key);
	}
	 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
