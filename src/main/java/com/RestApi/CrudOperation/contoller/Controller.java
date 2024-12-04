package com.RestApi.CrudOperation.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.RestApi.CrudOperation.entity.Student;
import com.RestApi.CrudOperation.repository.StudentRepository;

@RestController
public class Controller {
	@Autowired
	StudentRepository repo;
	
	@GetMapping("/students")
public List<Student> getAllstudents(){
		List<Student> students = repo.findAll();
	return students;
	
}
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		return student;
		
	}
	@PostMapping("/students")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Student createStudent(@RequestBody Student student) {
		return repo.save(student);
	}
	
	@PutMapping("/students/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Student updateStudent(@PathVariable int id, @RequestBody Student s) {
		Student student = repo.findById(id).get();
		student.setName(s.getName());
		student.setPercentage(s.getPercentage());
		student.setBranch(s.getBranch());
		repo.save(student);
		return student;
		
	}
	
	@DeleteMapping("students/{id}")
	public void removeStudent(@PathVariable int id) {
		 repo.deleteById(id);
		
	}
	
	@GetMapping("students/sort/{field}/{direction}")
	public List<Student> sortStudent(@PathVariable String field,@PathVariable String direction) {
		Sort.Direction direction2=direction.equalsIgnoreCase("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
		List<Student> students = repo.findAll(Sort.by(direction2, field));
		return students;
		
	}
	
	@GetMapping("students/pagination/{offset}/{pageSize}")
	public Page<Student> findStudentsWithPagination(@PathVariable int offset,@PathVariable int pageSize){
		Page<Student> students = repo.findAll(PageRequest.of(offset, pageSize));
		return students;
		
	}
	@GetMapping("/students/paginationAndSort/{offset}/{pageSize}/{field}")
	public Page<Student> findSudentsWithPaginationAndSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field) {
		Page<Student> students = repo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
		return students;
	}
}
