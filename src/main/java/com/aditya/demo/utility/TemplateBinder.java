package com.aditya.demo.utility;

import com.aditya.demo.model.TemplateConfig;
import com.aditya.demo.service.TemplateStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TemplateBinder {

    private final TemplateStorageService templateStorageService;
    private static final int MAX_DEPTH = 5;
    private static final Logger log = LoggerFactory.getLogger(TemplateBinder.class);



    public TemplateBinder(TemplateStorageService templateStorageService) {
        this.templateStorageService = templateStorageService;
    }

    public String bindTemplate(String templateKey) {
        return bindRecursive(templateKey, new HashSet<>(),0);


    }

    private String bindRecursive(String templateKey, Set<String> visiting, int depth) {

        if (depth == MAX_DEPTH) {
            log.warn("Max depth exceeded for {}", templateKey) ;
            return "";
        }

        // --- CIRCULAR DEPENDENCY GUARD ---
        if (visiting.contains(templateKey)) {
            log.warn("Circular dependency found: " + templateKey);
            return "";
        }

        visiting.add(templateKey);
        TemplateConfig config = templateStorageService.getTemplateConfig(templateKey);

        if (config == null || config.content() == null) {
            visiting.remove(templateKey);
            return "";
        }

        String content = config.content();
        List<String> placeholders = config.requiredPlaceholders();

        for (String placeholder : placeholders) {

            // Keep original literal form {{placeholder}}
            String placeholderLiteral = "{{" + placeholder + "}}";

            // Fetch its template definition
            TemplateConfig dependencyConfig = templateStorageService.getTemplateConfig(placeholder);

            if (dependencyConfig == null) {
                // Nothing available to replace -> leave literal unchanged
                log.info(" No template found for placeholder: " + placeholder);
                continue;
            }

            // Recursively stitch dependency content
            String resolved = bindRecursive(placeholder, visiting,depth + 1);

            // Replace the {{placeholder}} occurrence
            content = content.replace(placeholderLiteral, resolved);
        }

        visiting.remove(templateKey);
        return content;
    }
}

