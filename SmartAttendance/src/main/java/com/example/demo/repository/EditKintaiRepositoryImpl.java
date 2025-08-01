package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.form.EditKintaiForm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EditKintaiRepositoryImpl implements EditKintaiRepository {
	
	private final JdbcTemplate jdbcTemplate;

	public void updateKintai(EditKintaiForm form) {
		String sql_updateKintai= "update attendance_info "
				+ "set "
				+ "	shift_id = ?, "
				+ "	start_H = ?, "
				+ "	start_M = ?, "
				+ "	end_H = ?, "
				+ "	end_M = ?, "
				+ "	total_workingTime = ?, "
				+ "	breakTime = ?, "
				+ "	excessTime = ? "
				+ "where emp_id = ? and record_date = ?";
		
		jdbcTemplate.update(sql_updateKintai, 
				form.getShift_ID(),
				form.getStart_H(),
				form.getStart_M(),
				form.getEnd_H(),
				form.getEnd_M(),
				form.getTotal_workingTime(),
				form.getBreakTime(),
				form.getExcessTime(),
				form.getEmp_id(),
				form.getRecord_date());
	}
	
	public void deleteKintai(EditKintaiForm form) {
		String sql_deleteKintai= "delete from attendance_info "
						+ "where emp_id = ? "
						+ "and record_date = ?";
		
		jdbcTemplate.update(sql_deleteKintai, form.getEmp_id(), form.getRecord_date());
	}
	
	public void addDays(EditKintaiForm form) {
		String sql_addSubDayString = "update pto_info set subHolidays_left = subHolidays_left + 1 where emp_id = ?";
		String sql_addPaidDays = "update pto_info set paidHolidays_left = paidHolidays_left + 1 where emp_id = ?";
		
		switch (form.getShift_ID()) {
		case 80: 
			jdbcTemplate.update(sql_addPaidDays, form.getEmp_id());
		
		case 90:
			jdbcTemplate.update(sql_addSubDayString, form.getEmp_id());
		}
	}
}
