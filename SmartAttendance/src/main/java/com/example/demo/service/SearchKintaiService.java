package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SearchResult;
import com.example.demo.form.SearchConditionForm;
import com.example.demo.form.SearchConditionMasterForm;

public interface SearchKintaiService {
	public List<SearchResult> search(String emp_id, SearchConditionForm form);
	public List<SearchResult> search(String emp_id, SearchConditionMasterForm form);
}
