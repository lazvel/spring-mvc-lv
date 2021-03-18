package it.engineering.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String index() {
		System.out.println("================ METHOD ================");
		System.out.println("public String index()");
		return "my-index"; // logicko ime ?
	}
}
