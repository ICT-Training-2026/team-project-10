package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.form.LoginUserForm;

@Controller
public class MainController {
	@GetMapping("/")
	public String showLoginForm(Model model) {
		model.addAttribute("loginUserForm", new LoginUserForm());
		return "login";
	}
}
