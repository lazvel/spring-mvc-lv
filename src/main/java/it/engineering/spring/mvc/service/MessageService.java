package it.engineering.spring.mvc.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
	private String message;
	
	public MessageService() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
