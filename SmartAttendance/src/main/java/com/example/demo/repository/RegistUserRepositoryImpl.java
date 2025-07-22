package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.form.RegistUserForm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistUserRepositoryImpl implements RegistUserRepository {

	private final JdbcTemplate jdbcTemplate;

	public Boolean UserCheck(String emp_id) {
		String sql_userCheck = "SELECT EXISTS (									" +
				"SELECT 1								" +
				"FROM									" +
				"	employees_info ei					" +
				"WHERE									" +
				"	ei.emp_id = ?						" +
				")";

		Boolean result = jdbcTemplate.queryForObject(sql_userCheck, Boolean.class, emp_id);
		return result;
	}

	public void RegistUser(RegistUserForm form) {
		String sql_userRegiString = "INSERT INTO employees_info "
				+ "(emp_id, emp_name, dep_id, hashed_pw, permission) "
				+ "VALUES (?,?,?,?,?)";

		HashedString hs = new HashedString();
		String hashed_pw = hs.hash_sha256(form.getPassword());

		jdbcTemplate.update(sql_userRegiString,
				form.getEmp_id(),
				form.getEmp_name(),
				form.getDep_id(),
				hashed_pw,
				form.isPermission());
	}
}
