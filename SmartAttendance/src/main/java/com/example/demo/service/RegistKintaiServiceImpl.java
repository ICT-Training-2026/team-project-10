package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RegistKintaiEntity;
import com.example.demo.form.RegistKintaiForm;
import com.example.demo.repository.RegistKintaiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistKintaiServiceImpl implements RegistKintaiService {
	
	
	LocalDate localDate = LocalDate.now();
	Date today = Date.valueOf(localDate);
	
	private final RegistKintaiRepository repository;
	@Override
	public void insert(RegistKintaiForm form,LoginUser loginUser) {
		RegistKintaiEntity entity = new RegistKintaiEntity();
		entity.setEmp_id(loginUser.getEmp_id());
		entity.setRecord_date(today);
		entity.setShift_ID(form.getShift_ID());
		entity.setStart_H(form.getStart_H());
		entity.setStart_M(form.getStart_M());
		entity.setEnd_H(form.getEnd_H());
		entity.setEnd_M(form.getEnd_M());
		entity.setTotal_workingTime(form.getTotal_workingTime());
		entity.setBreakTime(form.getBreakTime());
		entity.setExcessTime(form.getExcessTime());
		repository.add(entity);
	}

}
