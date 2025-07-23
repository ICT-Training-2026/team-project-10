package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.RegistUserForm;
import com.example.demo.service.RegistUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistUserController {

	private final RegistUserService service;

	@PostMapping("/regist-user")
	public String registUser(@ModelAttribute LoginUser loginUser, Model model) {
		model.addAttribute("registUserForm", new RegistUserForm());
		model.addAttribute("loginUser", loginUser);
		return "regist-user";
	}

	@PostMapping("/confirm-regist-user")
	public String cofirmRegistUser(@ModelAttribute RegistUserForm form, @ModelAttribute LoginUser loginUser, Model model) {
		Boolean check = service.UserCheck(form.getEmp_id());

		model.addAttribute("registUserForm", form);
		model.addAttribute("loginUser", loginUser);

		if (!check) {
			model.addAttribute("registUserForm", form);
			return "confirm-regist-user";
		} else {
			form.setMsgflag(true);
			model.addAttribute("registUserForm", form);
			return "regist-user";
		}
	}

	@PostMapping("complete-regist-user")
	public String completeRegistUser(@ModelAttribute RegistUserForm form, @ModelAttribute LoginUser loginUser, Model model) {
		service.RegistUser(form);
		model.addAttribute("loginUser", loginUser);
		return "complete";
	}

}
