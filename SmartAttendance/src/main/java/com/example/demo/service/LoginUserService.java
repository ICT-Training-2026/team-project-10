package com.example.demo.service;

import com.example.demo.entity.LoginUser;

public interface LoginUserService {

	LoginUser findUser(String emp_id, String password);
}
