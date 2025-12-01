package com.aditya.demo.controller;

import com.aditya.demo.model.Department;
import com.aditya.demo.model.Employee;
import com.aditya.demo.service.PdfGenerationService;
import com.aditya.demo.utility.TemplateBinder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Controller
public class ConsolidationController {

    private final TemplateBinder templateBinder;
    private final TemplateEngine stringTemplateEngine;
    private final PdfGenerationService pdfGenerationService;

    public ConsolidationController(
            TemplateBinder templateBinder,
            TemplateEngine stringTemplateEngine,
            PdfGenerationService pdfGenerationService) {
        this.templateBinder = templateBinder;
        this.stringTemplateEngine = stringTemplateEngine;
        this.pdfGenerationService = pdfGenerationService;}

    private Context getReportContext(boolean isConsolidated) {
        Context context = new Context();

        context.setVariable("employees", List.of(
                new Employee("Alice", "Manager", 90000),
                new Employee("Bob", "Analyst", 65000)
        ));

        context.setVariable("departments", List.of(
                new Department("HR", 50),
                new Department("Finance", 100)
        ));

        context.setVariable("isConsolidated", isConsolidated);

        return context;
    }


    @GetMapping("/report/consolidated")
    public ResponseEntity<byte[]> generateConsolidatedReport() throws Exception {

        String stitchedHTML = templateBinder.bindTemplate("CONSOLIDATED_MASTER");
        String renderedHtml = stringTemplateEngine.process(stitchedHTML, getReportContext(true));
        byte[] pdfBytes = pdfGenerationService.generatePdfFromHtml(renderedHtml);
        return getPdfResponse(pdfBytes, "Consolidated_Report.pdf");
    }

//    @PostConstruct
//    private void init() {
//        this.CONSOLIDATED_MASTER= templateStorageService.getTemplateContent("CONSOLIDATED_MASTER") ;
//    }
//
//    private Context getReportContext() {
//        Context context = new Context();
//
//        context.setVariable("employees", List.of(
//                new Employee("Alice", "Manager", 90000),
//                new Employee("Bob", "Analyst", 65000)
//        ));
//
//        context.setVariable("departments", List.of(
//                new Department("HR", 50),
//                new Department("Finance", 100)
//        ));
//
//        context.setVariable("reportDate", LocalDate.now().toString());
//        context.setVariable("employeeId", 123); // For the history fragment
//
//        return context;
//    }
//
//    @GetMapping("/report/department")
//    public ResponseEntity<byte[]> generateDepartmentReport() throws Exception {
//        String rawDepartmentHtml = templateStorageService.getTemplateContent("departmentReport");
//
//
//        String finalStitchedHtml = CONSOLIDATED_MASTER
//                .replace("{REPORT_ONE}", rawDepartmentHtml)
//                .replace("{REPORT_TWO}", "<div></div>");
//
//        String renderedHtml = stringTemplateEngine.process(finalStitchedHtml, getReportContext());
//        byte[] pdfBytes = pdfGenerationService.generatePdfFromHtml(renderedHtml);
//
//        return getPdfResponse(pdfBytes, "Department_Report.pdf");
//    }
//
//    @GetMapping("/report/employee-with-history")
//    public ResponseEntity<byte[]> generateComplexEmployeeReport() throws Exception {
//        String employeeFragment = templateStorageService.getTemplateContent("employeeReport");
//        String historyFragment = templateStorageService.getTemplateContent("historyReport");
//
//        //getChildTemplates
//        // get all child templates
//        // render child/hide child
//
//        String employeeReportWithHistory = employeeFragment
//                .replace("{historyTemplate}", historyFragment);
//
//        String finalStitchedHtml = CONSOLIDATED_MASTER
//                .replace("{REPORT_ONE}", employeeReportWithHistory)
//                .replace("{REPORT_TWO}", "<div></div>");
//
//        String renderedHtml = stringTemplateEngine.process(finalStitchedHtml, getReportContext());
//        byte[] pdfBytes = pdfGenerationService.generatePdfFromHtml(renderedHtml);
//
//        return getPdfResponse(pdfBytes, "Employee_History_Report.pdf");
//    }
//
//    @GetMapping("/report/consolidated")
//    public ResponseEntity<byte[]> generateConsolidatedReport() throws Exception {
//        String employeeFragment = templateStorageService.getTemplateContent("employeeReport");
//        String departmentFragment = templateStorageService.getTemplateContent("departmentReport");
//
//        String cleanEmployeeFragment = employeeFragment
//                .replace("{historyTemplate}", "");
//
//        String finalStitchedHtml = CONSOLIDATED_MASTER
//                .replace("{REPORT_ONE}", cleanEmployeeFragment)
//                .replace("{REPORT_TWO}", departmentFragment);
//
//        String renderedHtml = stringTemplateEngine.process(finalStitchedHtml, getReportContext());
//        byte[] pdfBytes = pdfGenerationService.generatePdfFromHtml(renderedHtml);
//
//        return getPdfResponse(pdfBytes, "Consolidated_Report.pdf");
//    }

    private ResponseEntity<byte[]> getPdfResponse(byte[] pdfBytes, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", filename);
        headers.setContentLength(pdfBytes.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
