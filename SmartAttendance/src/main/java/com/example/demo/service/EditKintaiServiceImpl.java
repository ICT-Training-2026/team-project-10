package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;

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
	
	public void checkRecordDate(EditKintaiForm form) {
		LocalDate localDate = LocalDate.now();
		Date today = Date.valueOf(localDate);
		
		Date record_date = java.sql.Date.valueOf(form.getRecord_date());
		
		if (record_date.compareTo(today) == 1) {
			repository.addDays(form);
		}
	}
}
