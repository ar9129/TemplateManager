package com.aditya.demo.controller;

import com.aditya.demo.service.TemplateStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private final TemplateStorageService templateStorageService;
    public AdminController(TemplateStorageService templateStorageService) {
        this.templateStorageService = templateStorageService;
    }

    @PostMapping("/admin/refresh-templates")
    public ResponseEntity<String> refreshTemplates() {
        templateStorageService.refreshTemplates();
        return ResponseEntity.ok("Templates refreshed successfully.");
    }
}
