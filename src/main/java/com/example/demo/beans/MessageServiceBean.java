package com.example.demo.beans;

import com.example.demo.interfaces.IMessageService;

import org.springframework.context.annotation.Bean;

public class MessageServiceBean {
	@Bean
	IMessageService mockMessageService(){
		return new IMessageService(){
		
			@Override
			public String getMessage() {
				return "Hello World!";
			}
		};
	}
}
