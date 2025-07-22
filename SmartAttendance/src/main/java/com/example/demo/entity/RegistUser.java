package com.example.demo.entity;

import lombok.Data;

@Data
public class RegistUser {
	private String emp_id;
	private String emp_name;
	private int dep_id;
	private String password;
	private boolean permission;
	private boolean msgflag;
}
