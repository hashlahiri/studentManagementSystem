package com.chanakya.sms.service;

import java.util.List;

import com.chanakya.sms.entity.Student;

public interface StudentService {

	// to list all students
	public List<Student> findAll();

	// to find the students by their id
	public Student findById(long theId);

	// to save the students
	public boolean save(Student theStudent);

	// to delete a student
	public void deleteById(long theId);

}
