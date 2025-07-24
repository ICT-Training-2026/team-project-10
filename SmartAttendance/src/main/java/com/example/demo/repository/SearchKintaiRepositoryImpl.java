package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SearchResult;
import com.example.demo.form.SearchConditionForm;
import com.example.demo.form.SearchConditionMasterForm;

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

	
//	master検索で使用
	private String sql_master_all = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.emp_id = ? AND ai.record_date = ? AND di.dep_id = ? AND ai.shift_ID = ?";

	private String sql_noShiftId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.emp_id = ? AND ai.record_date = ? AND di.dep_id = ?";

	private String sql_noDepId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.emp_id = ? AND ai.record_date = ? AND ai.shift_ID = ?";

	private String sql_noDate = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.emp_id = ? AND di.dep_id = ? AND ai.shift_ID = ?";

	private String sql_noEmpId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.record_date = ? AND di.dep_id = ? AND ai.shift_ID = ?";

	private String sql_empIdAndDate = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.emp_id = ? AND ai.record_date = ?";

	private String sql_empIdAndDepId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.emp_id = ? AND di.dep_id = ?";

	private String sql_empIdAndShiftId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.emp_id = ? AND ai.shift_ID = ?";

	private String sql_dateAndDepId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.record_date = ? AND di.dep_id = ?";

	private String sql_dateAndShiftId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.record_date = ? AND ai.shift_ID = ?";

	private String sql_depIdAndShiftId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE di.dep_id = ? AND ai.shift_ID = ?";

	private String sql_onlyEmpId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.emp_id = ?";

	private String sql_master_onlyDate = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.record_date = ?";

	private String sql_onlyDepId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE di.dep_id = ?";

	private String sql_master_onlyShiftId = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id "
			+ "WHERE ai.shift_ID = ?";

	private String sql_noConditions = "SELECT ai.emp_id, ei.emp_name, di.dep_id, ai.record_date, ai.shift_ID, "
			+ "ai.start_H, ai.start_M, ai.end_H, ai.end_M, ai.total_workingTime, ai.breakTime, ai.excessTime "
			+ "FROM attendance_info ai "
			+ "INNER JOIN employees_info ei ON ai.emp_id = ei.emp_id "
			+ "INNER JOIN department_info di ON ei.dep_id = di.dep_id";

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

	public List<SearchResult> search(String emp_id, SearchConditionMasterForm form) {

		List<SearchResult> results = new ArrayList<SearchResult>();

		List<Map<String, Object>> list;

		System.out.println(form);

		if (form.getEmp_id() != "" && !form.getDate().equals("") && form.getDep_id() != 0 && form.getShift_id() != 0) {
			// 全ての条件が指定されている場合
			list = jdbcTemplate.queryForList(sql_master_all, form.getEmp_id(), form.getDate(), form.getDep_id(), form.getShift_id());
		} else if (form.getEmp_id() != "" && !form.getDate().equals("") && form.getDep_id() != 0 && form.getShift_id() == 0) {
			// emp_id, record_date, dep_idが指定されている場合
			list = jdbcTemplate.queryForList(sql_noShiftId, form.getEmp_id(), form.getDate(), form.getDep_id());
		} else if (form.getEmp_id() != "" && !form.getDate().equals("") && form.getDep_id() == 0 && form.getShift_id() != 0) {
			// emp_id, record_date, shift_idが指定されている場合
			list = jdbcTemplate.queryForList(sql_noDepId, form.getEmp_id(), form.getDate(), form.getShift_id());
		} else if (form.getEmp_id() != "" && form.getDate().equals("") && form.getDep_id() != 0 && form.getShift_id() != 0) {
			// emp_id, dep_id, shift_idが指定されている場合
			list = jdbcTemplate.queryForList(sql_noDate, form.getEmp_id(), form.getDep_id(), form.getShift_id());
		} else if (!form.getDate().equals("") && form.getDep_id() != 0 && form.getShift_id() != 0 && form.getEmp_id() == "") {
			// record_date, dep_id, shift_idが指定されている場合
			list = jdbcTemplate.queryForList(sql_noEmpId, form.getDate(), form.getDep_id(), form.getShift_id());
		} else if (form.getEmp_id() != "" && !form.getDate().equals("") && form.getDep_id() == 0 && form.getShift_id() == 0) {
			// emp_idとrecord_dateのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_empIdAndDate, form.getEmp_id(), form.getDate());
		} else if (form.getEmp_id()!= "" && form.getDate().equals("") && form.getDep_id() != 0 && form.getShift_id() == 0) {
			// emp_idとdep_idのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_empIdAndDepId, form.getEmp_id(), form.getDep_id());
		} else if (form.getEmp_id() != "" && form.getDate().equals("") && form.getDep_id() == 0 && form.getShift_id() != 0) {
			// emp_idとshift_idのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_empIdAndShiftId, form.getEmp_id(), form.getShift_id());
		} else if (form.getEmp_id() == "" && !form.getDate().equals("") && form.getDep_id() != 0 && form.getShift_id() == 0) {
			// record_dateとdep_idのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_dateAndDepId, form.getDate(), form.getDep_id());
		} else if (form.getEmp_id() == "" && !form.getDate().equals("") && form.getDep_id() == 0 && form.getShift_id() != 0) {
			// record_dateとshift_idのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_dateAndShiftId, form.getDate(), form.getShift_id());
		} else if (form.getEmp_id() == "" && form.getDate().equals("") && form.getDep_id() != 0 && form.getShift_id() != 0) {
			// dep_idとshift_idのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_depIdAndShiftId, form.getDep_id(), form.getShift_id());
		} else if (form.getEmp_id() != "" && form.getDate().equals("") && form.getDep_id() == 0 && form.getShift_id() == 0) {
			// emp_idのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_onlyEmpId, form.getEmp_id());
		} else if (form.getEmp_id() == "" && !form.getDate().equals("") && form.getDep_id() == 0 && form.getShift_id() == 0) {
			// record_dateのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_master_onlyDate, form.getDate());
		} else if (form.getEmp_id() == "" && form.getDate().equals("") && form.getDep_id() != 0 && form.getShift_id() == 0) {
			// dep_idのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_onlyDepId, form.getDep_id());
		} else if (form.getEmp_id() == "" && form.getDate().equals("") && form.getDep_id() == 0 && form.getShift_id() != 0) {
			// shift_idのみが指定されている場合
			list = jdbcTemplate.queryForList(sql_master_onlyShiftId, form.getShift_id());
		} else {
			// 全ての条件が指定されていない場合
			list = jdbcTemplate.queryForList(sql_noConditions);
		}

		for (Map<String, Object> one : list) {
			results.add(ssr.setresult(one));
		}

		return results;
	}

}
