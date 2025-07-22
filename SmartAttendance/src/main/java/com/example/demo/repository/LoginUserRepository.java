package com.example.demo.repository;

import com.example.demo.entity.LoginUser;

public interface LoginUserRepository {
	public LoginUser findUser(String emp_id, String password);
}
