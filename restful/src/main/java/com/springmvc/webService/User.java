package com.springmvc.webService;

import java.util.Date;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
	private Integer id;
	
	@Size(min=2, message="至少需要輸入2個字母")
	private String name;
	
	@Past
	private Date birthDate;
	
	public User(Integer id, String name, Date birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
}
