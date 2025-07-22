package com.example.demo.form;

import lombok.Data;

@Data
public class LoginUserForm {
	private String emp_id;
	private int dep_id;
	private String password;
	private boolean permission;
	private boolean msgflag;
}
