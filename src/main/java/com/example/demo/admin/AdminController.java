package com.example.demo.admin;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class AdminController {
	@RequestMapping("/admin")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getQueryString());
		String string = "<html><head><title>Admin</title></head><body><h2>Admin</h2><nav><a href=\"/hello\">hello</a>&nbsp;<a href=\"/about\">about</a></nav>";
		// String text = "<p style=\"margin:0\">.abcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+</p>";
		return string;
	}
}
