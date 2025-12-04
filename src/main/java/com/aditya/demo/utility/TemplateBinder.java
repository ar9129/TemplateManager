package com.aditya.demo.utility;

import com.aditya.demo.constants.TemplateConstants;
import com.aditya.demo.exception.*;
import com.aditya.demo.model.TemplateConfig;
import com.aditya.demo.service.TemplateLoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TemplateBinder {

    private final TemplateLoaderService templateLoaderService;
    private static final int MAX_DEPTH = 3;
    private static final Logger log = LoggerFactory.getLogger(TemplateBinder.class);



    public TemplateBinder(TemplateLoaderService templateLoaderService) {
        this.templateLoaderService = templateLoaderService;
    }

    public String bindTemplate(String templateKey) {
        return bindRecursive(templateKey, new HashSet<>(),0);


    }

    private String bindRecursive(String templateKey, Set<String> visiting, int depth) {

        if (templateKey == null || templateKey.isBlank()) {
            throw new ValidationException(templateKey);
        }

        if (depth >= MAX_DEPTH) {
            log.warn("Max depth exceeded for {}", templateKey);
            throw new MaxDepthExceededException(templateKey, MAX_DEPTH);
        }

        if (visiting.contains(templateKey)) {
            log.warn("Circular dependency found: {}", templateKey);
            throw new CircularReferenceException(templateKey);
        }

        visiting.add(templateKey);

        try {
            TemplateConfig config = templateLoaderService.getTemplateConfig(templateKey);

            if (config == null || config.content() == null) {
                throw new TemplateNotFoundException(templateKey);
            }

            String content = config.content();
            List<String> placeholders = config.requiredPlaceholders();

            for (String placeholder : placeholders) {

                if (placeholder == null || placeholder.isBlank()) {
                    throw new ValidationException(placeholder);
                }

                if (!TemplateConstants.PLACEHOLDER_REGEX.matcher("{{" + placeholder + "}}").matches()) {
                    throw new InvalidReferenceSyntaxException(placeholder);
                }

                String literal = TemplateConstants.toPlaceholderLiteral(placeholder);

                // Recursively bind placeholder content
                String resolved = bindRecursive(placeholder, visiting, depth + 1);

                // Replace placeholder in current template
                content = content.replace(literal, resolved);

                log.debug("Resolved placeholder {} in template {}", placeholder, templateKey);
            }

            return content;

        } finally {
            visiting.remove(templateKey);
        }
    }
}

