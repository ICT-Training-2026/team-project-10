package com.example.demo.repository;

import java.sql.Date;

import com.example.demo.entity.RegistKintaiEntity;

public interface RegistKintaiRepository {
	public void add(RegistKintaiEntity entity);
	public Boolean kintaiCheck(String emp_id, Date record_date);
}
