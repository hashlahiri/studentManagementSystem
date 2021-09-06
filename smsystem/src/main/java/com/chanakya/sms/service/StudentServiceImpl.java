package com.chanakya.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chanakya.sms.entity.Student;
import com.chanakya.sms.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository theStudentRepository) {
		studentRepository = theStudentRepository;
	}

	@Override
	public List<Student> findAll() {

		// returns a list of all students
		return studentRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Student findById(long theId) {

		// to handle crud repository results
		Optional<Student> result = studentRepository.findById(theId);

		Student theStudent = null;

		if (result.isPresent()) {
			theStudent = result.get();
		} else {
			throw new RuntimeException("Did not find the student" + theStudent);
		}

		return theStudent;
	}

	@Override
	public boolean save(Student theStudent) {
		// adding the student in database
		theStudent = studentRepository.save(theStudent);

		if (theStudent != null && theStudent.getStudentNo() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteById(long theId) {
		// deleting the student
		studentRepository.deleteById(theId);
	}
}
