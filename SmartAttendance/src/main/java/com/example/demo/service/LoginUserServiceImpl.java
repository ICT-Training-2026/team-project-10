package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.repository.LoginUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserServiceImpl implements LoginUserService{
	
	private final LoginUserRepository repository;
	
	public LoginUser findUser(String emp_id, String password) {
		LoginUser result = repository.findUser(emp_id, password);
		
		return result;
	}
}
