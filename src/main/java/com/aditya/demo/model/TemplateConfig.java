package com.aditya.demo.model;

import java.util.List;

public record TemplateConfig(
        String content,
        List<String> requiredTemplates,
        List<String> requiredPlaceholders
){

}