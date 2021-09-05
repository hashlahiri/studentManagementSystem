package com.chanakya.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chanakya.sms.entity.Student;

//used jpa for help related to CRUD operations, reducing complexity and boiler plate code
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	// adding a method to sort it out by lastName of students
	public List<Student> findAllByOrderByLastNameAsc();

}
