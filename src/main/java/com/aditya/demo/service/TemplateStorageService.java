package com.aditya.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aditya.demo.model.TemplateConfig;
import com.aditya.demo.model.TemplateEntity;
import com.aditya.demo.repository.TemplateRepository;
import com.aditya.demo.utility.TemplateParserUtility;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class TemplateStorageService {

    private final TemplateRepository templateRepository;

    private final TemplateParserUtility templateParserUtility;


    private final Map<String, String> templates = new HashMap<>();

    private volatile Map<String, TemplateConfig> templateRegistry = new HashMap<>();


    public TemplateStorageService(TemplateRepository templateRepository, TemplateParserUtility templateParserUtility) {
        this.templateRepository = templateRepository;
        this.templateParserUtility = templateParserUtility;
    }

    @PostConstruct
    public void init() {
        loadAndDiscoverTemplates() ;
    }

    private void loadAndDiscoverTemplates() {
        List<TemplateEntity> templates = templateRepository.findAll();
        Map<String, TemplateConfig> newTemplateRegistry = new HashMap<>();
        for (TemplateEntity template : templates) {
            List<String>subTemplates = templateParserUtility.discoverSubTemplate(template.getTemplateContent()) ;
            List<String>placeholders = templateParserUtility.discoverPlaceHolders(template.getTemplateContent()) ;

            TemplateConfig config = new TemplateConfig(template.getTemplateContent(), subTemplates, placeholders);
            newTemplateRegistry.put(template.getTemplateName(), config);

        }
        this.templateRegistry = newTemplateRegistry;

    }

    public TemplateConfig getTemplateConfig(String templateKey) {
        return templateRegistry.get(templateKey);
    }
}
