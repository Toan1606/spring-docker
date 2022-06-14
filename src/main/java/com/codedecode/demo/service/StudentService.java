package com.codedecode.demo.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.one_to_many.Student;
import com.codedecode.demo.entity.paging.StudentPage;
import com.codedecode.demo.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<Student> getStudents(StudentPage studentPage){
        Sort sort = Sort.by(studentPage.getSortDirection(), studentPage.getSortBy());
        Pageable pageable = PageRequest.of(studentPage.getPageNumber(),
                studentPage.getPageSize(), sort);
        return studentRepository.findAll(pageable);
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }
    
}