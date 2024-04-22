package com.springmvc.webService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/1") //@RequestMapping(method=RequestMethod.GET, path="/1")
	public String helloWorld() {
		return "12345678";
	}
	
	@GetMapping("/name/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
}
