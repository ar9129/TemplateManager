package com.aditya.demo.repository;

import com.aditya.demo.loader.TemplateLoader;
import com.aditya.demo.model.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TemplateRepositoryImpl implements TemplateRepository {

    private final List<TemplateEntity> templates;

    public TemplateRepositoryImpl(TemplateLoader templateLoader) {
        templates = templateLoader.loadTemplates();
    }

    @Override
    public List<TemplateEntity> findAll() {
        return templates;
    }
}
