package com.example.demo.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.example.demo.entity.SearchResult;

public class SetSearchResult {
	public SearchResult setresult(Map<String, Object> one, String emp_name) {
		SearchResult sr = new SearchResult();
		sr.setEmp_id((String)one.get("emp_id"));
		sr.setEmp_name(emp_name);
		sr.setRecord_Date((Date)one.get("record_Date"));
		sr.setShift_id((Integer)one.get("shift_ID"));
		sr.setStart_H((Integer)one.get("start_H"));
		sr.setStart_M((Integer)one.get("start_M"));
		sr.setEnd_H((Integer)one.get("end_H"));
		sr.setEnd_M((Integer)one.get("end_M"));
		sr.setTotal_workingTime((BigDecimal)one.get("total_workingTime"));
		sr.setBreakTime((Integer)one.get("breakTime"));
		sr.setExcessTime((Integer)one.get("breakTime"));
		return sr;
	}
}
