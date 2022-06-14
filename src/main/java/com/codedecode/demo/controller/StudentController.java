package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.one_to_many.Student;
import com.codedecode.demo.entity.paging.StudentPage;
import com.codedecode.demo.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

	@Autowired
	private final StudentService studentService;

	@PostMapping(path = "/add")
	public ResponseEntity<Student> addStudent() {
		Student student1 = new Student(1L, "Eric");
		Student result = studentService.addStudent(student1);
//		Student result = studentService.addStudent(student);
		return new ResponseEntity<Student>(result, HttpStatus.CREATED);
	}

	@GetMapping(path = "/page/{pageNumber}")
	public ResponseEntity<Page<Student>> getStudents(StudentPage studentPage) {
		return new ResponseEntity<Page<Student>>(studentService.getStudents(studentPage), HttpStatus.OK);
	}
}