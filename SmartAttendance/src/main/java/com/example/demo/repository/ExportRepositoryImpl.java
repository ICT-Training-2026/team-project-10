package com.example.demo.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SearchResult;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExportRepositoryImpl implements ExportRepository {

	private final JdbcTemplate jdbcTemplate;

	private final SetSearchResult ssr = new SetSearchResult();

	public void export() {

		List<SearchResult> results = new ArrayList<SearchResult>();

		LocalDate currentDate = LocalDate.now().minusMonths(1);;
		String currentYearMonth = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
		System.out.println(currentDate);

		String sql_kintaiAll = "select * "
				+ "from attendance_info "
				+ "where DATE_FORMAT(record_date, '%Y-%m') = DATE_FORMAT(\"" + currentDate + "\", '%Y-%m');";

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql_kintaiAll);

		String fileName = "./attendance-csv/" + currentYearMonth + ".csv";

		createFile(list, fileName);

		for (Map<String, Object> one : list) {
			results.add(ssr.setresult(one));
		}

	}

	public void createFile(List<Map<String, Object>> list, String csvFile) {
		try (FileWriter fw = new FileWriter(csvFile, false);) {
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.print("ID");
            pw.print(",");
            pw.print("record_date");
            pw.print(",");
            pw.print("shift_id");
            pw.print(",");
            pw.print("start_H");
            pw.print(",");
            pw.print("start_M");
            pw.print(",");
            pw.print("end_H");
            pw.print(",");
            pw.print("end_M");
            pw.print(",");
            pw.print("total_workingTime");
            pw.print(",");
            pw.print("breakTime");
            pw.print(",");
            pw.print("excessTime");
            pw.println();
            
            for (Map<String, Object> one : list) {
            	pw.print(one.get("emp_id"));
            	pw.print(",");
            	pw.print(one.get("record_date").toString());
            	pw.print(",");
            	pw.print(one.get("shift_id"));
            	pw.print(",");
            	pw.print(one.get("start_H"));
            	pw.print(",");
            	pw.print(one.get("start_M"));
            	pw.print(",");
            	pw.print(one.get("end_H"));
            	pw.print(",");
            	pw.print(one.get("end_M"));
            	pw.print(",");
            	pw.print(one.get("total_workingTime"));
            	pw.print(",");
            	pw.print(one.get("breakTime"));
            	pw.print(",");
            	pw.print(one.get("excessTime"));
            	pw.println();
            }
            
            pw.close();

			System.out.println("CSVファイルが正常に作成されました。");

		} catch (IOException e) {
			System.out.println("CSVファイルの作成中にエラーが発生しました。");
			e.printStackTrace();
		}
	}
}
