package com.example.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
	
	@NotBlank(message = "First name is required.")
	private String firstName;
	
	@NotBlank(message = "Last name is required.")
	private String lastName;
	
	private String email;

}
