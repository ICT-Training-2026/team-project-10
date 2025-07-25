package com.example.demo.service;

import java.sql.Date;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.SubHolidayForm;

public interface SubHolidayService {
	public int getSubHoliday(String empId);
	public void insert(SubHolidayForm form,LoginUser loginUser) ;
	public Boolean kintaiCheck(String emp_id, Date date);
}
