package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HomeController {
	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getQueryString());
		String string = "<html><head><title>Home</title></head><body><h2>Home</h2><nav><a href=\"/hello\">hello</a>&nbsp;<a href=\"/about\">about</a></nav>";
		String text = "<p style=\"margin:0\">.abcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+</p>";
		text += text;
		text += text;
		text += text;
		text += text;
		text += text;
		text += text;
		text += text;
		text += text;
		text += text;
		text += text;
		text += text;
		text += text;
		string += text;
		string += text;
		string += "</body></html>";
		return string;
	}
}
