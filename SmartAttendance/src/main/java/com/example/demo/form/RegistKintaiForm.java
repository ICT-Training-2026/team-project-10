package com.example.demo.form;

import java.sql.Date;

import lombok.Data;
@Data
public class RegistKintaiForm {
		private String emp_id;
		private Date record_date;
		private int shift_ID;
		private int start_H;
		private int start_M;
		private int end_H;
		private int end_M;
		private double total_workingTime;/*実労働時間[時間]*/
		private double breakTime; /*休憩時間[時間]*/
		private double excessTime; /*超過労働時間[時間]*/

}
