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
	
	LoginUser lu = new LoginUser();

	@PostMapping("/regist-kintai")
	public String registKintai(@ModelAttribute LoginUser loginUser, RegistKintaiForm form, Model model) {
		model.addAttribute("registKintaiForm", new RegistKintaiForm());
		mainController.setter("勤怠登録", loginUser);
		lu = loginUser;
		return "regist-kintai";
	}

	/*登録実行*/
	@PostMapping("/confirm-regist-kintai")
	public String showConfirmRegistKintai(@ModelAttribute RegistKintaiForm form, Model model) {
		model.addAttribute("registKintaiForm", form);
		model.addAttribute("loginUser", lu);
		if (service.kintaiCheck(lu.getEmp_id())) {
			model.addAttribute("msg", "すでに登録されています。修正する場合は検索から編集を行ってください。");
			return "regist-kintai";
		} else {
			if (form.getStart_H() > form.getEnd_H()) {
				model.addAttribute("msg", "始業・終業時刻が不適切です。");
				return "regist-kintai";
			} else if(form.getStart_H() >= form.getEnd_H() && form.getStart_M() >= form.getEnd_M()) {
				model.addAttribute("msg", "始業・終業時刻が不適切です。");
				return "regist-kintai";
			} 
			if (form.getTotal_workingTime() >= 4.00) {
				model.addAttribute("msg", "最低一時間以上の休憩時間を入力してください。");
				return "regist-kintai";
			}
			if (form.getTotal_workingTime() <= 0) {
				model.addAttribute("msg", "実労働時間が入力されていません。");
				return "regist-kintai";
			}
			
			return "confirm-regist-kintai";
		}
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
