package com.dheeraj.StudentRestApi.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dheeraj.StudentRestApi.model.Student;

public interface StudentRepositery extends JpaRepository<Student, Long> {

}
