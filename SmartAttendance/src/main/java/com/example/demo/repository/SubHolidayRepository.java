package com.example.demo.repository;

import com.example.demo.entity.PtoInfo;
import com.example.demo.entity.SubHoliday;

public interface SubHolidayRepository {
	public int get(String empId);
	public void addAttendanceInfo(SubHoliday entity);
	public void addPtoInfo(PtoInfo ptoinfo);

}
