package com.example.demo.repository;

import java.sql.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RegistKintaiEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistKintaiRepositoryImpl implements RegistKintaiRepository {
	private final JdbcTemplate jdbcTemplate;

	public void add(RegistKintaiEntity entity) {
		String sql = "INSERT INTO attendance_info (emp_id,record_date,shift_ID,start_H,start_M,end_H,end_M,total_workingTime,breakTime,excessTime) VALUES(?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, entity.getEmp_id(), entity.getRecord_date(), entity.getShift_ID(), entity.getStart_H(),
				entity.getStart_M(), entity.getEnd_H(), entity.getEnd_M(), entity.getTotal_workingTime(),
				entity.getBreakTime(), entity.getExcessTime());
	}

	public Boolean kintaiCheck(String emp_id, Date record_date) {
		String sql_kintaiCheck = "SELECT EXISTS (									" +
				"SELECT 1								" +
				"FROM									" +
				"	attendance_info ai					" +
				"WHERE									" +
				"	ai.emp_id = ? and ai.record_date = ?						" +
				")";
		Boolean result = jdbcTemplate.queryForObject(sql_kintaiCheck, Boolean.class, emp_id, record_date);
		return result;
	}

}
