package it.engineering.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.engineering.spring.mvc.service.MessageService;


// @Controller da nisam anotirao a da ga koristim
@RequestMapping(path = "/home")
public class HomeController {
	private MessageService messageService;
	
	@Autowired
	public HomeController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		messageService.setMessage("Ovo je poruka!");
		System.out.println(messageService.getMessage());
		return new ModelAndView("home");
	}
}
