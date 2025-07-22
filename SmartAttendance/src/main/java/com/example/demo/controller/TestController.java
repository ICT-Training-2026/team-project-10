package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.RegistUserForm;
import com.example.demo.service.RegistUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {
	
	private final RegistUserService service;
	
	@GetMapping("/regist-user")
	public String registUser(Model model) {
		model.addAttribute("registUserForm", new RegistUserForm());
		return "regist-user";
	}
	
	@PostMapping("/confirm-regist-user")
	public String cofirmRegistUser(@ModelAttribute RegistUserForm form, Model model) {
		Boolean check = service.UserCheck(form.getEmp_id());
		
		model.addAttribute("registUserForm", form);
		
		if (!check) {
			model.addAttribute("registUserForm", form);
			return "confirm-regist-user";
		}
		else {
			form.setMsgflag(true);
			model.addAttribute("registUserForm", form);
			return "regist-user"; 
		}
	}
	
	@PostMapping("complete-regist-user")
	public String completeRegistUser(@ModelAttribute RegistUserForm form, Model model) {
		service.RegistUser(form);
		return "complete";
		
	}
		
	
}
