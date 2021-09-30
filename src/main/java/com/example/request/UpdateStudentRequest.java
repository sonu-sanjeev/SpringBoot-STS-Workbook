package com.example.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class UpdateStudentRequest {
	
	@NotNull(message = "Id is required.")
	private Integer id;
	
	private String firstName;
	

}
