package com.example.demo.entity;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class SearchResult {
	private String emp_id;
	private String emp_name;
	private Date record_Date;
	private int shift_id;
	private int start_H;
	private int start_M;
	private int end_H;
	private int end_M;
	private BigDecimal total_workingTime;
	private int breakTime;
	private int excessTime;
}
