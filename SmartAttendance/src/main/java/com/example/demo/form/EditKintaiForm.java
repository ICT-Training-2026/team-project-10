package com.example.demo.form;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EditKintaiForm {
	private String emp_id;
	private String emp_name;
	private String record_date;
	private int shift_ID;
	private int start_H;
	private int start_M;
	private int end_H;
	private int end_M;
	private BigDecimal total_workingTime;
	private int breakTime;
	private int excessTime;
}
