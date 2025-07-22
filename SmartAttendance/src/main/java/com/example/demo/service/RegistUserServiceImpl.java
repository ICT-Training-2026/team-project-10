package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.form.RegistUserForm;
import com.example.demo.repository.RegistUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistUserServiceImpl implements RegistUserService {
    
    private final RegistUserRepository repository;

    public Boolean UserCheck(String emp_id) {
        return repository.UserCheck(emp_id);
    }
    
    public void RegistUser(RegistUserForm form) {
    	repository.RegistUser(form);
    }
}