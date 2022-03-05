package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

}
