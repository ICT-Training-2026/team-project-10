package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.LoginUserForm;

@Controller
public class MainController {
	
	String msg = null;
	LoginUser lu = null;
	
	@GetMapping("/")
	public String showLoginForm(Model model) {
		model.addAttribute("loginUserForm", new LoginUserForm());
		return "login";
	}
	
	/*Menu*/
	@PostMapping("/menu")
	public String showMenu(@ModelAttribute LoginUser loginUser, Model model) {
		model.addAttribute("loginUser", loginUser);
		return "main";
	}
	
	@GetMapping("/complete")
	public String complete(Model model) {
		model.addAttribute("msg", msg);
		model.addAttribute("loginUser", lu);
		return "complete";
	}

	public void setter(String message, LoginUser loginUser) {
		msg = message;
		lu = loginUser;
	}

}
