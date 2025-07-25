package com.example.demo.service;

import java.sql.Date;

public interface RegistPaidHolidayService {
	public int remainingDaysCheck(String emp_id);
	public void insertKintai(String emp_id, Date date);
	public Boolean kintaiCheck(String emp_id, Date date);
}
