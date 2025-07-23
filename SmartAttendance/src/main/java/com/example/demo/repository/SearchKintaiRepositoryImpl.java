package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SearchResult;
import com.example.demo.form.SearchConditionForm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchKintaiRepositoryImpl implements SearchKintaiRepository {

	private final JdbcTemplate jdbcTemplate;
	private final SetSearchResult ssr = new SetSearchResult();
	private String sql_getName = "select emp_name "
								+ "from employees_info "
								+ "where emp_id = ?";

	private String sql_all = "select * from attendance_info "
							+ "where record_Date = ? "
							+ "and shift_ID = ? "
							+ "and emp_id = ?";
	
	private String sql_onlyDate = "select * from attendance_info "
								+ "where record_Date = ?"
								+ "and emp_id = ?"; 
	
	private String sql_onlyShiftId = "select * from attendance_info "
								+ "where shift_ID = ? "
								+ "and emp_id = ? "
								+ "order by record_Date desc";

	public List<SearchResult> search(String emp_id, SearchConditionForm form) {

		List<SearchResult> results = new ArrayList<SearchResult>();
		
		List<Map<String, Object>> list;
		
		System.out.println(form);

		if (form.getDate() != "" && form.getShift_id() != 0) {
			list = jdbcTemplate.queryForList(sql_all, form.getDate(), form.getShift_id(), emp_id);
		} else if (form.getDate() != "" && form.getShift_id() == 0) {
			list = jdbcTemplate.queryForList(sql_onlyDate, form.getDate(), emp_id);
		} else {
			list = jdbcTemplate.queryForList(sql_onlyShiftId, form.getShift_id(), emp_id);
		}
		
		for (Map<String, Object> one : list) {
			String emp_name = jdbcTemplate.queryForObject(sql_getName, String.class, emp_id);
			results.add(ssr.setresult(one, emp_name));
		}

		return results;
	}

}
