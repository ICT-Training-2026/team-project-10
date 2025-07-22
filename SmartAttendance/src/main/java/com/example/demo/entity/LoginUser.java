package com.example.demo.entity;

import lombok.Data;

@Data
public class LoginUser {
	private String emp_id;
	private int dep_id;
	private String password;
	private boolean permission;
	private boolean msgflag;
}
