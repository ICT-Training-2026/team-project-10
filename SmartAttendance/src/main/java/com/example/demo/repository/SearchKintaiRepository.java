package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.SearchResult;
import com.example.demo.form.SearchConditionForm;

public interface SearchKintaiRepository {
	public List<SearchResult> search(String emp_id, SearchConditionForm form);
}
