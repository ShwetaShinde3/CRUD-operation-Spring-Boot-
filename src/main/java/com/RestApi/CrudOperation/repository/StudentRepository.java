package com.RestApi.CrudOperation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RestApi.CrudOperation.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
