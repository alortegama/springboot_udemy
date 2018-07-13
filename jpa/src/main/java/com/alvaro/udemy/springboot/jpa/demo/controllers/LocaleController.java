package com.alvaro.udemy.springboot.jpa.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LocaleController {

	@GetMapping("/locale")
	public String locale(HttpServletRequest request){
		String ultimaURL= request.getHeader("referer");
		return "redirect:".concat(ultimaURL);
	}
}
