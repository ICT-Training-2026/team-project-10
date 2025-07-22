package com.example.demo.repository;

import com.example.demo.form.RegistUserForm;

public interface RegistUserRepository {
	public Boolean UserCheck(String emp_id);

	public void RegistUser(RegistUserForm form);
}
