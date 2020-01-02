package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about!";
	}
}
