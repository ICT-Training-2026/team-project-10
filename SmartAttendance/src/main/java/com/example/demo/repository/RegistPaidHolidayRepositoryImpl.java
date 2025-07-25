package com.example.demo.repository;

import java.sql.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistPaidHolidayRepositoryImpl implements RegistPaidHolidayRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	String sql_remainingDays = "select paidHolidays_left from pto_info where emp_id = ?";
	
	String sql_insertKintai = "insert into attendance_info(emp_id, record_date, shift_id, start_H, start_M, end_H, end_M, total_workingTime, breakTime, excessTime)\n"
			+ "values(?, ?, 80, 0, 0, 0, 0, 7, 0, 0)";
	
	String sql_decrementPaidHolidayLeft = "update pto_info set paidHolidays_left = paidHolidays_left - 1 where emp_id = ?";
	
	String sql_kintaiCheck = "";
	
	public int remainingDaysCheck(String emp_id) {
		int result = jdbcTemplate.queryForObject(sql_remainingDays, Integer.class, emp_id);
		return result;
	}
	
	public void insertKintai(String emp_id, Date date) {
		jdbcTemplate.update(sql_insertKintai, emp_id, date);
		jdbcTemplate.update(sql_decrementPaidHolidayLeft, emp_id);
	}
	
	public Boolean kintaiCheck(String emp_id, Date date) {
		String sql_kintaiCheck = "SELECT EXISTS (									" +
				"SELECT 1								" +
				"FROM									" +
				"	attendance_info ai					" +
				"WHERE									" +
				"	ai.emp_id = ? and ai.record_date = ?						" +
				")";
		Boolean result = jdbcTemplate.queryForObject(sql_kintaiCheck, Boolean.class, emp_id, date);
		return result;
	}
}
