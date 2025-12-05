package com.aditya.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aditya.demo.model.TemplateConfig;
import com.aditya.demo.model.TemplateEntity;
import com.aditya.demo.repository.TemplateRepository;
import com.aditya.demo.template.TemplateUtility;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class TemplateLoaderService {

    private final TemplateRepository templateRepository;

    private final TemplateUtility templateUtility;

    private volatile Map<String, TemplateConfig> templateRegistry = new HashMap<>();


    public TemplateLoaderService(TemplateRepository templateRepository, TemplateUtility templateUtility) {
        this.templateRepository = templateRepository;
        this.templateUtility = templateUtility;
    }

    @PostConstruct
    public void init() {
        loadAndDiscoverTemplates() ;
    }

    private void loadAndDiscoverTemplates() {
        List<TemplateEntity> templates = templateRepository.findAll();
        Map<String, TemplateConfig> newTemplateRegistry = new HashMap<>();
        for (TemplateEntity template : templates) {
            List<String>placeholders = templateUtility.discoverPlaceHolders(template.getTemplateContent()) ;

            TemplateConfig config = new TemplateConfig(template.getTemplateContent(), placeholders);
            newTemplateRegistry.put(template.getTemplateName(), config);

        }
        this.templateRegistry = newTemplateRegistry;

    }

    public void refreshTemplates() {
        loadAndDiscoverTemplates();
    }

    public TemplateConfig getTemplateConfig(String templateKey) {
        return templateRegistry.get(templateKey);
    }
}
