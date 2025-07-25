package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.form.EditKintaiForm;
import com.example.demo.repository.EditKintaiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditKintaiServiceImpl implements EditKintaiService {
	
	private final EditKintaiRepository repository;
	
	public void updateKintai(EditKintaiForm form) {
		repository.updateKintai(form);
	}
	
	public void deleteKintai(EditKintaiForm form) {
		repository.deleteKintai(form);
	}
}
