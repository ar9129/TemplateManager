package com.aditya.demo.controller;

import com.aditya.demo.service.TemplateLoaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private final TemplateLoaderService templateLoaderService;
    public AdminController(TemplateLoaderService templateLoaderService) {
        this.templateLoaderService = templateLoaderService;
    }

    @PostMapping("/admin/refresh-templates")
    public ResponseEntity<String> refreshTemplates() {
        templateLoaderService.refreshTemplates();
        return ResponseEntity.ok("Templates refreshed successfully.");
    }
}
