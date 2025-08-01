package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.SearchResult;
import com.example.demo.form.EditKintaiForm;
import com.example.demo.form.SearchConditionForm;
import com.example.demo.form.SearchConditionMasterForm;
import com.example.demo.service.SearchKintaiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SearchKintaiController {
	
	private final SearchKintaiService service;
	LoginUser lu = new LoginUser();
	
	@PostMapping("/search-kintai")
	public String searchKintai(@ModelAttribute LoginUser loginUser, Model model) {
		lu = loginUser;
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("editKintaiForm", new EditKintaiForm());
		if (lu.isLogin_permission()) {
			model.addAttribute("searchConditionMasterForm", new SearchConditionMasterForm());
			return "search-kintai-master";
		} else {
			model.addAttribute("searchConditionForm", new SearchConditionForm());
			return "search-kintai";
		}
	}
	
	@PostMapping("/search-result")
	public String searchResult(@ModelAttribute SearchConditionForm form, Model model) {
		List<SearchResult> result = service.search(lu.getLogin_emp_id(), form);
		model.addAttribute("loginUser", lu);
		model.addAttribute("searchConditionForm", form);
		model.addAttribute("searchResult_list", result);
		model.addAttribute("editKintaiForm", new EditKintaiForm());
		System.out.println(result);
		return "search-kintai";
	}
	
	@PostMapping("/search-master-result")
	public String searchMasterResult(@ModelAttribute SearchConditionMasterForm form, Model model) {
		List<SearchResult> result = service.search(lu.getLogin_emp_id(), form);
		model.addAttribute("loginUser", lu);
		model.addAttribute("searchConditionMasterForm", form);
		model.addAttribute("searchResult_list", result);
		model.addAttribute("editKintaiForm", new EditKintaiForm());
		return "search-kintai-master";
	}
}
