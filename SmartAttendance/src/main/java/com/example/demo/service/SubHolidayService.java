package com.example.demo.service;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.SubHolidayForm;

public interface SubHolidayService {
	public int getSubHoliday(String empId);
	public void insert(SubHolidayForm form,LoginUser loginUser) ;
}
