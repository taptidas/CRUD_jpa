package com.Crud.jpaTest.CrudProject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
@Configuration
@Entity
@Table(name = "employees")
public class Employee {
	//@Value("${emp.id}")
	private long id;
	@ApiModelProperty(notes = "employee's first name")
	private String firstName;
	@ApiModelProperty(notes = "employee's last name")
	private String lastName;
	@ApiModelProperty(notes = "employee's email id")
	private String emailId;

	public Employee() {

	}

	public Employee(String firstName, String lastName, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @ApiModelProperty(notes = "The database generated employee ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email_address", nullable = false)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}


