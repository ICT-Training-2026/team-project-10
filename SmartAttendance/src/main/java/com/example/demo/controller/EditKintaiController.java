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
	public String editKintai(@ModelAttribute LoginUser loginUser, @ModelAttribute EditKintaiForm editKintaiForm,
			Model model) {
		lu = loginUser;

		System.out.println(lu);
		model.addAttribute("editKintaiForm", editKintaiForm);
		model.addAttribute("loginUser", loginUser);
		return "edit-kintai";
	}

	@PostMapping({ "/delete-kintai", "/update-kintai" })
	public String processKintai(@ModelAttribute("editKintaiForm") EditKintaiForm form,
			@RequestParam("operation") String operation, @ModelAttribute LoginUser loginUser, Model model) {
		model.addAttribute("editKintaiForm", form);
		model.addAttribute("loginUser", lu);

		if ("delete".equals(operation)) {
			System.out.println("削除");
			mainController.setter("勤怠情報削除", lu);
			return "confirm-delete-kintai";
		} else {
			System.out.println("更新");
			if (8 <= form.getStart_H() && form.getEnd_H() <= 22) {
				if (form.getStart_H() > form.getEnd_H()) {
					model.addAttribute("msg", "始業・終業時刻が不適切です。");
					return "edit-kintai";
				} else if (form.getStart_H() >= form.getEnd_H() && form.getStart_M() >= form.getEnd_M()) {
					model.addAttribute("msg", "始業・終業時刻が不適切です。");
					return "edit-kintai";
				}
				if (form.getTotal_workingTime() >= 4.00 && form.getBreakTime() < 1) {
					model.addAttribute("msg", "最低一時間以上の休憩時間を入力してください。");
					return "edit-kintai";
				}
				if (form.getTotal_workingTime() <= 0) {
					model.addAttribute("msg", "実労働時間が入力されていません。");
					return "edit-kintai";
				}
			} else {
				model.addAttribute("msg", "始業・終業時刻は8時-22時の間で入力してください。");
				return "edit-kintai";
			}
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
		if (form.getStart_H() <= 0 && form.getEnd_H() <= 0) {
			service.checkRecordDate(form);
		}
		service.deleteKintai(form);
		return "redirect:/complete";
	}
}
