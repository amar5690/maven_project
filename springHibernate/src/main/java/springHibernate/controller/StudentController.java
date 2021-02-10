package springHibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springHibernate.entity.Student;
import springHibernate.service.IStudentService;

@RestController
public class StudentController {

	@Autowired
	IStudentService studentService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}
	
	@GetMapping("/getStudent")
	public List<Student> getStudent() {
		return studentService.getStudent();
	}
}
