package com.chanakya.sms.entity;

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
	private long studentId;

	private String studentFirstName;

	private String studentLastName;

	private String studentEmail;

	private String studentVaccineStatus;

}
