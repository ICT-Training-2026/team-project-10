package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoginUser;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginUserRepositoryImpl implements LoginUserRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public LoginUser findUser(String emp_id, String password) {
		HashedString hs = new HashedString();
		String hashed_passNumber = hs.hash_sha256(password);
		
		String sql_userCheck =
				"SELECT EXISTS (									" +
						"SELECT 1								" +
						"FROM									" +
						"	employees_info ei					" +
						"WHERE									" +
						"	ei.emp_id = ?	AND ei.hashed_pw = ?	" +
				")";
		
		String sql_getUser = 
			    "SELECT * "
			    + "FROM employees_info ei "
			    + "WHERE ei.emp_id = ?";
		
		Boolean result = jdbcTemplate.queryForObject(sql_userCheck ,Boolean.class, emp_id, hashed_passNumber);
		
		LoginUser findUser = new LoginUser();
		if (result) {
			List<Map<String, Object>> users = jdbcTemplate.queryForList(sql_getUser, emp_id);
			Map<String, Object> userMap = users.get(0);
			findUser.setEmp_id((String)userMap.get("emp_id"));
			findUser.setDep_id((Integer)userMap.get("dep_id"));
			findUser.setPermission((Boolean)userMap.get("permission"));
		} else {
			findUser.setEmp_id(emp_id);
			findUser.setMsgflag(true);
		}
		
		return findUser;
	}
}
