package com.example.demo.entity;

import lombok.Data;

@Data
public class LoginUser {
	private String login_emp_id;
	private int login_dep_id;
	private String password;
	private boolean login_permission;
	private boolean msgflag;
}
