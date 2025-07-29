package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.LoginUserForm;
import com.example.demo.service.LoginUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	private final LoginUserService service;

	@PostMapping("user_search")
	private String userSearch(@ModelAttribute LoginUserForm form, Model model) {
		LoginUser loginUser = service.findUser(form.getEmp_id(), form.getPassword());
		if (!loginUser.isMsgflag()) {
			model.addAttribute("loginUser", loginUser);
			return "main";
		}else {
			LoginUserForm retryForm = new LoginUserForm();
			retryForm.setEmp_id(form.getEmp_id());
			retryForm.setMsgflag(true);
			model.addAttribute("loginUserForm", retryForm);
			return "login";
		}
	}

}
