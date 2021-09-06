package com.chanakya.sms.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//The main database table interaction with java entity class
@Entity
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_no")
	private int studentNo;

	@Column(name="student_name")
	private String studentName;

	@Column(name="student_dob")
	private Date studentDob;

	@Column(name="student_doj")
	private Date studentDoj;

}
