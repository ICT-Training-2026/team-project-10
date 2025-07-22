package com.example.demo.service;

import com.example.demo.form.RegistUserForm;

public interface RegistUserService {
	public Boolean UserCheck(String emp_id);
	public void RegistUser(RegistUserForm form);
}
