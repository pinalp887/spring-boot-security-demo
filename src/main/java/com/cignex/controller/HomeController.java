package com.cignex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HomeController {
	@GetMapping("/acess-denied")
	public String mainPage() {
		System.out.println("into acess denied");
		return "main";
	}

	@GetMapping("/admin")
	public String home() {
		System.out.println("called after login");
		return "index";
	}
	@GetMapping("/user")
	public String user() {
		System.out.println("called after login");
		return "home";
	}
	@GetMapping("/logout-sucess")
	public String logOut() {
		return "logout";
	}
}
