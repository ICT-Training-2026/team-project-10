package com.example.demo.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.PtoInfo;
import com.example.demo.entity.SubHoliday;
import com.example.demo.form.SubHolidayForm;
import com.example.demo.repository.SubHolidayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubHolidayServiceImpl implements SubHolidayService{
	private final SubHolidayRepository repository;
	@Override
	public int getSubHoliday(String empId) {
		return repository.get(empId);
	}
	@Override
	public void insert(SubHolidayForm form,LoginUser loginUser) {
		SubHoliday entity = new SubHoliday();
		entity.setEmp_id(loginUser.getEmp_id());
		entity.setRecord_date(form.getRecord_date());
		repository.addAttendanceInfo(entity);
		
		PtoInfo ptoinfo = new PtoInfo();
		ptoinfo.setEmp_id(loginUser.getEmp_id());
		ptoinfo.setSubHolidaysLeft(form.getSubHolidaysLeft()-1);
		repository.addPtoInfo(ptoinfo);
	}
	
	public Boolean kintaiCheck(String emp_id, Date date) {
		return repository.kintaiCheck(emp_id, date);
	}
}
