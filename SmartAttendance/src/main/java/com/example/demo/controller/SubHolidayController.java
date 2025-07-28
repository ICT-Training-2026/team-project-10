package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.SubHolidayForm;
import com.example.demo.service.SubHolidayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SubHolidayController {
	private final MainController mainController;
	private final SubHolidayService service;
	LoginUser lu = new LoginUser();
	LocalDate localDate = LocalDate.now();
	Date today = Date.valueOf(localDate);

	@PostMapping("/regist-subHoliday")
	public String registKintai(@ModelAttribute LoginUser loginUser, SubHolidayForm form, Model model) {
		mainController.setter("振休申請", loginUser);
		lu = loginUser;
		int hCount = service.getSubHoliday(lu.getLogin_emp_id());
		form.setSubHolidaysLeft(hCount); // SubHolidayFormに設定
		
		model.addAttribute("remaining", form.getSubHolidaysLeft());
		model.addAttribute("subHolidayForm", form);
		model.addAttribute("loginUser", lu);
		return "regist-subHoliday";
	}

	@PostMapping("/confirm-regist-subHoliday")
	public String showConfirmRegistSubHoliday(@ModelAttribute SubHolidayForm form, Model model) {
		model.addAttribute("subHolidayForm", form);
		model.addAttribute("loginUser", lu);
		System.out.println(form.getSubHolidaysLeft());
		if (service.kintaiCheck(lu.getLogin_emp_id(), form.getRecord_date())) {
			model.addAttribute("msg", "すでに勤怠登録されています。");
			return "regist-subHoliday";
		}
		if (form.getSubHolidaysLeft() == 0) {
			model.addAttribute("msg", "残振休数がありません。");
			return "regist-subHoliday";
		} else if (form.getRecord_date().before(today)) {
			model.addAttribute("msg", "本日より前の日付を選択しています。");
			return "regist-subHoliday";
		} else {
			return "confirm-regist-subHoliday";
		}
	}

	/*確認ページから戻る*/
	@PostMapping("/Modoru-regist-subHoliday")
	public String showModoru(@ModelAttribute SubHolidayForm form, Model model) {
		model.addAttribute("subHolidayForm", form);
		model.addAttribute("loginUser", lu);
		return "regist-subHoliday";
	}

	@PostMapping("/complete-regist-subHoliday")
	public String showcomplete(@ModelAttribute SubHolidayForm form, Model model) {
		service.insert(form, lu);
		model.addAttribute("loginUser", lu);
		return "redirect:/complete";
	}

}