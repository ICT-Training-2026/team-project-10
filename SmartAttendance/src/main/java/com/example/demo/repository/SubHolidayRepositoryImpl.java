package com.example.demo.repository;

import java.sql.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PtoInfo;
import com.example.demo.entity.SubHoliday;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SubHolidayRepositoryImpl implements SubHolidayRepository{
	private final JdbcTemplate jdbcTemplate;
	public int get(String empId) {
		String sql = "SELECT subHolidays_left FROM pto_info WHERE emp_id = ?";
		Integer hCount = jdbcTemplate.queryForObject(sql, Integer.class,empId);
		return hCount;
	}
	
	public void addAttendanceInfo(SubHoliday entity) {
		String sql = "INSERT INTO attendance_info (emp_id,record_date,shift_ID,start_H,start_M,end_H,end_M,total_workingTime,breakTime,excessTime) VALUES(?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,entity.getEmp_id(),entity.getRecord_date(),entity.getShift_ID(),entity.getStart_H(),entity.getStart_M(),entity.getEnd_H(),entity.getEnd_M(),entity.getTotal_workingTime(),entity.getBreakTime(),entity.getExcessTime());
		
	}
	
	public void addPtoInfo(PtoInfo ptoinfo) {
		String sql = "UPDATE pto_info SET subHolidays_left=? WHERE emp_id=? ";
		jdbcTemplate.update(sql,ptoinfo.getSubHolidaysLeft(),ptoinfo.getEmp_id());
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
