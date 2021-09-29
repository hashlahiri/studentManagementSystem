package com.chanakya.sms.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chanakya.sms.entity.Student;
import com.chanakya.sms.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	private StudentService studentService;

	// constructor injection
	@Autowired
	public StudentController(StudentService theStudentService) {
		studentService = theStudentService;
	}

	// the url list under students
	@GetMapping("/list")
	public ResponseEntity<?> listStudents(Model theModel) {

		// get list of students
		List<Student> theStudent = studentService.findAll();

		// add the list to the spring model
		theModel.addAttribute("student", theStudent);

		// using hashmap to get the response
		HashMap<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", "FAILURE");
		responseMap.put("message", "Failed to load...Try again");

		if (theStudent != null) {
			responseMap.put("status", "SUCCESS");
			responseMap.put("message", "");
			responseMap.put("studentList", theStudent);
		}

		// returns a hashmap as response
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	// add a new student
	@PostMapping("/addStudent")
	public ResponseEntity<?> addStudent(@RequestBody Student student) {

		// save the student
		boolean addStatus = studentService.save(student);

		// hashmap created for response
		HashMap<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", "FAILURE");
		responseMap.put("message", "Student could not be added try again");

		if (addStatus) {
			responseMap.put("status", "SUCCESS");
			responseMap.put("message", "Student added successufully");
		}
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@PutMapping("/updateStudentById/{id}")
	public ResponseEntity<?> updateStudentById(@RequestBody Student newStudent, @PathVariable String id) {
		// get student from service layer
		Student theStudent = studentService.findById(Integer.parseInt(id));

		if (theStudent == null) {
			throw new RuntimeException("Student not found hence not updated");
		} else {
			theStudent.setStudentName(newStudent.getStudentName());
			theStudent.setStudentDob(newStudent.getStudentDob());
			theStudent.setStudentDoj(newStudent.getStudentDoj());

			boolean updateStatus = studentService.save(theStudent);

			HashMap<String, Object> responseMap = new HashMap<>();
			responseMap.put("status", "FAILURE");
			responseMap.put("message", "Student was not updated");

			if (updateStatus) {
				responseMap.put("status", "SUCCESS");
				responseMap.put("message", "Student updated successufully");
			}
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable String id) {

		// get student credentials
		Student theStudent = studentService.findById(Integer.parseInt(id));

		if (theStudent == null) {
			throw new RuntimeException("Student not found, hence not deleted");
		} else {
			studentService.deleteById(Integer.parseInt(id));

			HashMap<String, Object> responseMap = new HashMap<>();
			responseMap.put("status", "SUCCESS");
			responseMap.put("message", "Student deleted successfully");

			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		}
	}
}