package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.EditKintaiForm;
import com.example.demo.service.EditKintaiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditKintaiController {
	
	private final MainController mainController;
	private final EditKintaiService service;
	
	LoginUser lu = new LoginUser();

	@PostMapping("/edit-kintai")
	public String editKintai(@ModelAttribute LoginUser loginUser, @ModelAttribute EditKintaiForm editKintaiForm, Model model) {
		lu = loginUser;
		
		System.out.println(editKintaiForm);
		model.addAttribute("editKintaiForm", editKintaiForm);
		model.addAttribute("loginUser", loginUser);
		return "edit-kintai";
	}

	@PostMapping({ "/delete-kintai", "/update-kintai" })
	public String processKintai(@ModelAttribute("editKintaiForm") EditKintaiForm editKintaiForm, @RequestParam("operation") String operation, Model model) {
		model.addAttribute("editKintaiForm", editKintaiForm);
		model.addAttribute("loginUser", lu);
		if ("delete".equals(operation)) {
			System.out.println("削除");
			mainController.setter("勤怠情報削除", lu);
			return "confirm-delete-kintai";
		} else {
			System.out.println("更新");
			mainController.setter("勤怠情報編集", lu);
			return "confirm-edit-kintai";
		}
	}
	
	@PostMapping("/complete-update-kintai")
	public String completeUpdateKintai(@ModelAttribute EditKintaiForm form, Model model) {
		service.updateKintai(form);
		return "redirect:/complete";
	}
	
	@PostMapping("/complete-delete-kintai")
	public String completeDeleteKintai(@ModelAttribute EditKintaiForm form, Model model) {
		service.deleteKintai(form);
		return "redirect:/complete";
	}
}
