package com.aditya.demo.repository;

import com.aditya.demo.model.Employee;
import com.aditya.demo.model.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TemplateRepository {
    List<TemplateEntity> findAll() ;
}
