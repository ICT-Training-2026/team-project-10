package com.example.demo.entity;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
@Data
public class RegistKintaiEntity {
		private String emp_id;
		private Date record_date;
		private int shift_ID;
		private int start_H;
		private int start_M;
		private int end_H;
		private int end_M;
		private BigDecimal total_workingTime;/*実労働時間[時間]*/
		private int breakTime; /*休憩時間[時間]*/
		private int excessTime; /*超過労働時間[時間]*/

}
