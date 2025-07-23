package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SearchResult;
import com.example.demo.form.SearchConditionForm;
import com.example.demo.repository.SearchKintaiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchKintaiServiceImpl implements SearchKintaiService {
	private final SearchKintaiRepository repository;

	@Override
	public List<SearchResult> search(String emp_id, SearchConditionForm form) {
		if (form.getDate() == "" && form.getShift_id() == 0) return null;
		else {
			List<SearchResult> result = repository.search(emp_id, form);
			return result;
		}
	} 
}
