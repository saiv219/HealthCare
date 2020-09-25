package com.crud.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="dependents")
public class Dependents {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message = "name is required")
	@Column(name="name")
	private String name;

	
	@NotEmpty(message = "dob is required")
	@Column(name="dob")
	private String dob;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Enrol_ID")
    private Enrollees enrollees;

	public Dependents() {
	}
	
	

	public Dependents(int id, String name, String dob, Enrollees enrollees) {
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.enrollees = enrollees;
	}



	public Enrollees getEnrollees() {
		return enrollees;
	}

	public void setEnrollees(Enrollees enrollees) {
		this.enrollees = enrollees;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	
}
