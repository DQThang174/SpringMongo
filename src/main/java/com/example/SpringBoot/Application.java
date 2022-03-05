package com.example.SpringBoot;

import com.example.SpringBoot.model.Address;
import com.example.SpringBoot.model.Gender;
import com.example.SpringBoot.model.Student;
import com.example.SpringBoot.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.awt.desktop.SystemSleepEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address(
					"England",
					"London",
					"NE9"
			);
			String email = "quocthang@gmail.com";
			Student student = new Student(
					"Duong",
					"Thang", email,
					Gender.FEMALE,
					address,
					List.of("Computer","Football"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);
			// xử lý không được trùng lặp
			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));

			List<Student> students = mongoTemplate.find(query, Student.class);

			if (students.size() > 1){
				throw new IllegalStateException("timf thấy học sinh với email" + email);
			}

			if (students.isEmpty()){
				System.out.println("theem sinh vieen " + student);
				studentRepository.insert(student);
			}else {
				System.out.println(student + "already studen");
			}

			studentRepository.insert(student);
		};
	}
}
