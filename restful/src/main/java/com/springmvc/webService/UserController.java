package com.springmvc.webService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> returnAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User returnUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return user;
	}
	
	
//	@PostMapping("/users")
//	public User createUser(@RequestBody User user) {
//		return service.save(user);
//	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
		if(user == null) {
			throw new UserNotFoundException("user is null");
		}
		User newUser = service.save(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User delUser = service.deleteById(id);
		if(delUser == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}
}
