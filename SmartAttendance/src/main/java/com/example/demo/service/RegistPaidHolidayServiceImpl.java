package com.example.demo.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.example.demo.repository.RegistPaidHolidayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistPaidHolidayServiceImpl implements RegistPaidHolidayService{
	
	private final RegistPaidHolidayRepository repository;
	
	public int remainingDaysCheck(String emp_id) {
		return repository.remainingDaysCheck(emp_id);
	}
	
	public void insertKintai(String emp_id, Date date) {
		repository.insertKintai(emp_id, date);
	}
	
	public Boolean kintaiCheck(String emp_id, Date date) {
		return repository.kintaiCheck(emp_id, date);
	}
}
