package com.example.demo.form;

import lombok.Data;

@Data
public class RegistUserForm {
	private String emp_id;
	private String emp_name;
	private int dep_id;
	private String password;
	private boolean permission;
	private boolean msgflag;
}
