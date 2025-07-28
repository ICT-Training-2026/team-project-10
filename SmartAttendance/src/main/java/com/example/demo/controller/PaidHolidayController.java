package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.RegistPaidHolidayForm;
import com.example.demo.service.RegistPaidHolidayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaidHolidayController {
	
	LoginUser lu = new LoginUser();
	private final RegistPaidHolidayService service;
	private final MainController mainController;
	
	@PostMapping("/regist-paidHoliday")
	public String registPaidHoliday(@ModelAttribute LoginUser loginUser, Model model) {
		lu= loginUser;
		mainController.setter("有給申請", lu);
		int remainingDays = service.remainingDays(lu.getLogin_emp_id());
		model.addAttribute("remaining", remainingDays);
		model.addAttribute("registPaidHolidayForm", new RegistPaidHolidayForm());
		model.addAttribute("loginUser", lu);
		return "regist-paidHoliday";
	}
	
	@PostMapping("/confirm-regist-paidHoliday")
	public String confirmRegistPaidHoliday(@ModelAttribute RegistPaidHolidayForm form, Model model) {
		int remainingDays = service.remainingDays(lu.getLogin_emp_id());
		model.addAttribute("remaining", remainingDays);
		model.addAttribute("registPaidHolidayForm", form);
		model.addAttribute("loginUser", lu);
		if (service.remainingDaysCheck(lu.getLogin_emp_id()) <= 0) {
			model.addAttribute("msg", "有休残日数がありません。");
			return "regist-paidHoliday";
		} else {
			if (service.kintaiCheck(lu.getLogin_emp_id(), form.getDate())) {
				model.addAttribute("msg", "すでに勤怠登録されています。");
				return "regist-paidHoliday";
			} else {
				return "confirm-regist-paidHoliday";
			}
		}
	}
	
	@PostMapping("/return-regist-paidHoliday") 
	public String returnRegistPaidHoliday(@ModelAttribute RegistPaidHolidayForm form, Model model) {
		int remainingDays = service.remainingDays(lu.getLogin_emp_id());
		model.addAttribute("remaining", remainingDays);
		model.addAttribute("registPaidHolidayForm", form);
		model.addAttribute("loginUser", lu);
		return "regist-paidHoliday";
	}
	
	@PostMapping("/complete-regist-paidHoliday")
	public String completeRegistPaidHoliday(@ModelAttribute RegistPaidHolidayForm form, Model model) {
		service.insertKintai(lu.getLogin_emp_id(), form.getDate());
		model.addAttribute("loginUser", lu);
		return "redirect:/complete";
	}
}
