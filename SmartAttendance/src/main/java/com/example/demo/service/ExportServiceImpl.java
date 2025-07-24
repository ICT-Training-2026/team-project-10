package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ExportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {
	
	private final ExportRepository repository;
	
	public void export() {
		repository.export();
	}
}
