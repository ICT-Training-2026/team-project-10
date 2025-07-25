package com.example.demo.repository;

import java.sql.Date;

public interface RegistPaidHolidayRepository {
	public int remainingDaysCheck(String emp_id);
	public void insertKintai(String emp_id, Date date);
	public Boolean kintaiCheck(String emp_id, Date date);
}
