package com.aditya.demo.utility;

import com.aditya.demo.model.TemplateConfig;
import com.aditya.demo.service.TemplateStorageService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TemplateStitcher {

    private final TemplateStorageService templateStorageService;

    public TemplateStitcher(TemplateStorageService templateStorageService) {
        this.templateStorageService = templateStorageService;
    }

    public String stitchTemplate(String templateKey) {
        return stitchRecursive(templateKey, new HashSet<>());
    }

    private String stitchRecursive(String templateKey, Set<String> visiting) {

        // --- CIRCULAR DEPENDENCY GUARD ---
        if (visiting.contains(templateKey)) {
            System.out.println("⚠ Circular dependency detected: " + templateKey);
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
                System.out.println(" ⚠ No template found for placeholder: " + placeholder);
                continue;
            }

            // Recursively stitch dependency content
            String resolved = stitchRecursive(placeholder, visiting);

            // Replace the {{placeholder}} occurrence
            content = content.replace(placeholderLiteral, resolved);
        }

        visiting.remove(templateKey);
        return content;
    }
}

