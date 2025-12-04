package com.aditya.demo.service;


import com.aditya.demo.model.Department;
import com.aditya.demo.model.Employee;
import com.aditya.demo.utility.TemplateBinder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

import java.util.List;

@Service
public class ReportGenerationService {

    private final TemplateBinder templateBinder;
    private final TemplateEngine templateEngine;
    private final PdfGenerationService pdfGenerationService;

    public ReportGenerationService(TemplateBinder templateBinder,
                                   TemplateEngine templateEngine,
                                   PdfGenerationService pdfGenerationService) {
        this.templateBinder = templateBinder;
        this.templateEngine = templateEngine;
        this.pdfGenerationService = pdfGenerationService;
    }

    public byte[] generateConsolidatedReportPdf(String tenplateKey) throws Exception {
        // 1. Bind template
        String stitchedHtml = templateBinder.bindTemplate(tenplateKey);

        // 2. Render HTML with context
        String renderedHtml = templateEngine.process(stitchedHtml, createConsolidatedContext());

        // 3. Generate PDF
        return pdfGenerationService.generatePdfFromHtml(renderedHtml);
    }

    private Context createConsolidatedContext() {
        Context context = new Context();
        context.setVariable("employees", List.of(
                new Employee("Alice", "Manager", 90000),
                new Employee("Bob", "Analyst", 65000)
        ));
        context.setVariable("departments", List.of(
                new Department("HR", 50),
                new Department("Finance", 100)
        ));
        context.setVariable("isConsolidated", true);
        return context;
    }
}

