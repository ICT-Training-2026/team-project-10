package com.example.demo.entity;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class SubHoliday {
	private String emp_id;
	private Date record_date;
	private int shift_ID=90;
	private int start_H=0;
	private int start_M=0;
	private int end_H=0;
	private int end_M=0;
	private BigDecimal total_workingTime=BigDecimal.ZERO;
	private int breakTime =0;
	private int excessTime=0;
	

}
