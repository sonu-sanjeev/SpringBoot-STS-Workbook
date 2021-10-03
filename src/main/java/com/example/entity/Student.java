package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.request.StudentRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Owning side of the OneToOne relation. */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@OneToOne /* (fetch = FetchType.LAZY) */ 
	@JoinColumn(name = "address_id")
	@JsonIgnore
	private Address address;

	@Transient // We need to add this when the corresponding field is not a column in the
				// 'student' table.
	private String schoolName = "MySchool";

	public Student(StudentRequest studentRequest) {
		this.firstName = studentRequest.getFirstName();
		this.lastName = studentRequest.getLastName();
		this.email = studentRequest.getEmail();
	}

}
