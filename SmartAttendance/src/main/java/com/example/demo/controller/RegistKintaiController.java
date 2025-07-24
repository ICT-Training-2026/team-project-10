package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.RegistKintaiForm;
import com.example.demo.service.RegistKintaiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistKintaiController {
	private final RegistKintaiService service;
	private final MainController mainController;
	//	@GetMapping("/")
	//	public String show(@ModelAttribute RegistKintaiForm form,LoginUserForm login) {
	//		return "regist-kintai";
	//	}
	//	
	//	/*logout*/
	//	@GetMapping("/logout")
	//	public String showLoginForm(Model model) {
	//		model.addAttribute("loginUserForm", new LoginUserForm());
	//		return "login";
	//	}
	//	
	//	/*Menu*/
	//	@PostMapping("/menu")
	//	public String showMenu(@ModelAttribute LoginUser loginUser, Model model) {
	//		model.addAttribute("loginUser", loginUser);
	//		return "main";
	//	}

	LoginUser lu = new LoginUser();

	//	
	@PostMapping("/regist-kintai")
	public String registKintai(@ModelAttribute LoginUser loginUser, RegistKintaiForm form, Model model) {
		model.addAttribute("registKintaiForm", new RegistKintaiForm());
		mainController.setter("勤怠登録", loginUser);
		lu = loginUser;
		System.out.println(lu);
		return "regist-kintai";
	}

	/*登録実行*/
	@PostMapping("/confirm-regist-kintai")
	public String showConfirmRegistKintai(@ModelAttribute RegistKintaiForm form, Model model) {
		model.addAttribute("registKintaiForm", form);
		model.addAttribute("loginUser", lu);
		return "confirm-regist-kintai";
	}

	/*確認ページから戻る*/
	@PostMapping("/Modoru-regist-kintai")
	public String showModoru(@ModelAttribute RegistKintaiForm form, Model model) {
		model.addAttribute("registKintaiForm", form);
		model.addAttribute("loginUser", lu);
		return "regist-kintai";
	}

	@PostMapping("/complete-regist-kintai")
	public String showcomplete(@ModelAttribute RegistKintaiForm form, Model model) {
		service.insert(form, lu);
		model.addAttribute("loginUser", lu);
		return "redirect:/complete";

	}

}
