package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class SearchCondition {
	private Date date;
	private int shift_id;
}
