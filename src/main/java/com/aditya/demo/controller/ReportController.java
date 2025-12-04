package com.aditya.demo.controller;

import com.aditya.demo.model.Department;
import com.aditya.demo.model.Employee;
import com.aditya.demo.service.PdfGenerationService;
import com.aditya.demo.service.ReportGenerationService;
import com.aditya.demo.utility.TemplateBinder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@RestController
public class ReportController {

    private final ReportGenerationService reportGenerationService;

    public ReportController(
            ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;}


    @GetMapping("/api/v1/reports/{report}")
    public ResponseEntity<byte[]> generateConsolidatedReport(@PathVariable String report) throws Exception {
        try {
            byte[] pdfBytes = reportGenerationService.generateConsolidatedReportPdf(report);
            return getPdfResponse(pdfBytes, "Consolidated_Report.pdf");
        } catch (Exception e) {
            // Return JSON error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(("{\"error\":\"" + e.getMessage() + "\"}").getBytes());
        }
    }


    private ResponseEntity<byte[]> getPdfResponse(byte[] pdfBytes, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", filename);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
