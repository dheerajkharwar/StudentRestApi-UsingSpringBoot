package com.dheeraj.StudentRestApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dheeraj.StudentRestApi.Exception.StudentNotFoundException;
import com.dheeraj.StudentRestApi.model.Student;
import com.dheeraj.StudentRestApi.repositery.StudentRepositery;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

	@Autowired
	private StudentRepositery studentRepositery;

	@PostMapping("/student")
	Student newStudent(@RequestBody Student newStudent) {
		return studentRepositery.save(newStudent);
	}

	@PostMapping("/students")
	List<Student> newStudents(@RequestBody List<Student> newStudents) {
		return studentRepositery.saveAll(newStudents);
	}

	@GetMapping("/student/{id}")
	Student getStudentById(@PathVariable Long id) {
		return studentRepositery.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
	}

	@GetMapping("/student")
	List<Student> getStudent() {
		return studentRepositery.findAll();
	}

	@PutMapping("/student/{id}")
	Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id) {
		return studentRepositery.findById(id).map(student -> {
			student.setName(newStudent.getName());
			student.setAge(newStudent.getAge());
			student.setCity(newStudent.getCity());
			return studentRepositery.save(student);
		}).orElseThrow(() -> new StudentNotFoundException(id));
	}

	@DeleteMapping("/student/{id}")
	String deleteStudentById(@PathVariable Long id) {
		if(!studentRepositery.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		studentRepositery.deleteById(id);
		return "Student with id "+id+" has been deleted successfully.";
	}

	@DeleteMapping("/student")
	void deleteStudent() {
		studentRepositery.deleteAll();
	}
}
