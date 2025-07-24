package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.service.ExportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExportController {
	
	private final MainController mainController;
	private final ExportService service;
	
	@PostMapping("/export")
	public String export(@ModelAttribute LoginUser loginUser, Model model) {
		mainController.setter("勤怠情報エクスポート", loginUser);
		service.export();
		return "redirect:/complete";
	}
}
