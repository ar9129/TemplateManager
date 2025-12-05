package com.aditya.demo.service;


import com.aditya.demo.context.ReportContextBuilder;
import com.aditya.demo.template.TemplateBinder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

@Service
public class ReportGenerationService {

    private final TemplateBinder templateBinder;
    private final TemplateEngine templateEngine;
    private final PdfGenerationService pdfGenerationService;
    private final ReportContextBuilder contextBuilder;

    public ReportGenerationService(TemplateBinder templateBinder,
                                   TemplateEngine templateEngine,
                                   PdfGenerationService pdfGenerationService, ReportContextBuilder contextBuilder) {
        this.templateBinder = templateBinder;
        this.templateEngine = templateEngine;
        this.pdfGenerationService = pdfGenerationService;
        this.contextBuilder = contextBuilder;
    }

    public byte[] generateReportPdf(String templateKey) throws Exception {
        // 1. Bind template
        String stitchedHtml = templateBinder.bindTemplate(templateKey);

        //2. Create Context
        Context context = contextBuilder.buildContext();

        // 3. Render HTML with context
        String renderedHtml = templateEngine.process(stitchedHtml, context);

        // 4. Generate PDF
        return pdfGenerationService.generatePdfFromHtml(renderedHtml);
    }
}

