package com.dheeraj.StudentRestApi.Exception;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(Long id) {
		super("Could not found student with id "+id);
	}

}
