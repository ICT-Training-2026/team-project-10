package com.example.demo.repository;

import com.example.demo.form.EditKintaiForm;

public interface EditKintaiRepository {
	public void updateKintai(EditKintaiForm form);
	public void deleteKintai(EditKintaiForm form);
	public void addDays(EditKintaiForm form);
}
