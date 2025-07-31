package com.example.demo.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.LoginUser;
import com.example.demo.service.ExportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExportController {
	
	private final MainController mainController;
	private final ExportService service;
	
	@PostMapping("/export")
	public String export(@ModelAttribute LoginUser loginUser, Model model) {
		mainController.setter("勤怠情報エクスポート", loginUser);
		service.export();
		String fileName = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM")) + ".csv";
		return "redirect:/complete?from=export&file=" + fileName;
	}
	
	@GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam String file) {
        File csv = new File("attendance-csv", file);  // attendance-csv/2025-07.csv

        if (!csv.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(csv);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(csv.length())
                .body(resource);
    }
}
