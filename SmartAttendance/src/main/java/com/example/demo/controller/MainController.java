package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginUserForm;

@Controller
public class MainController {
	@GetMapping("/")
	public String showLoginForm(Model model) {
		model.addAttribute("loginUserForm", new LoginUserForm());
		return "login";
	}

	/*勤怠登録*/
	@PostMapping("/confirm-regist-kintai")
	public String showRegistKintai(@ModelAttribute LoginUserForm form) {
		return "confirm-regist-kinatai";
	}

	/*勤怠検索*/
	@PostMapping("/search-kintai")
	public String showSearchKintai(@ModelAttribute LoginUserForm form) {
		//		MainEntity r = new MainEntity();
		//		r.setEmp_id(form.getEmp_id());
		//		boolean result = service.a(r);
		//		
		//		if(result) {
		//			return "search-kintai";
		//		}
		//		else {
		//			return "search-kintai-master";
		//		}
		return "search-kintai";
	}

	/*有給休暇申請*/
	@PostMapping("/regist-paidHoliday")
	public String showRegistPaidHoliday(@ModelAttribute LoginUserForm form) {
		return "regist-paidHoliday";
	}

	/*振休申請*/
	@PostMapping("/regist-subHoliday")
	public String showRegistSubHoliday(@ModelAttribute LoginUserForm form) {
		return "regist-subHoliday";
	}

	/*新規ユーザー登録*/
	@PostMapping("/regist-user")
	public String showRegistUser(@ModelAttribute LoginUserForm form) {
		return "regist-user";
	}

	/*エクスポート*/
	@PostMapping("/export")
	public String showExport() {
		return "export";
	}

	/*Menu*/
	@PostMapping("/Menu")
	public String showMenu(@ModelAttribute LoginUserForm form) {
		return "main";
	}

	/*Logout*/
	@PostMapping("/logout")
	public String showLogout(@ModelAttribute LoginUserForm form) {
		return "login";
	}
}
