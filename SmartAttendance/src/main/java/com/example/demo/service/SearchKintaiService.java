package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SearchResult;
import com.example.demo.form.SearchConditionForm;

public interface SearchKintaiService {
	public List<SearchResult> search(String emp_id, SearchConditionForm form);
}
