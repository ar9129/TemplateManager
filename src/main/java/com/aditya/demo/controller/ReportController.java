//package com.aditya.demo.controller;
//
//import com.aditya.demo.model.Department;
//import com.aditya.demo.model.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.standard.expression.Fragment;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import java.io.ByteArrayOutputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class ReportController {
//
//    @Autowired
//    TemplateEngine templateEngine;
//
//
//    List<Employee> employees = List.of(
//            new Employee("XYZ", "SALES", 20000),
//            new Employee("XYZ2", "Marketing", 30000)
//    );
//
//    List<Department> departments = List.of(
//            new Department("SALES", 200),
//            new Department("Marketing", 100)
//    );
//
//
//    @GetMapping("/download")
//    public ResponseEntity<byte[]> downloadPdf() throws Exception {
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("totalEmployees", 100);
//        data.put("totalDepartments", 5);
//        data.put("employees", employees);
//        data.put("departments", departments);
//
//        Context context = new Context();
//        context.setVariables(data);
//
//        String html = templateEngine.process("employee", context);
//        html = html.replace("&nbsp;", " ");
//        html = html.trim();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ITextRenderer renderer = new ITextRenderer();
//
//        renderer.setDocumentFromString(html);
//        renderer.layout();
//        renderer.createPDF(outputStream);
//
//        byte[] pdfBytes = outputStream.toByteArray();
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(pdfBytes);
//    }
//
//
//    @GetMapping("/report/employee")
//    public String generateReportEmployee(Model model) {
//        model.addAttribute("employees", employees);
//        return "employee";  // Thymeleaf template name
//    }
//
//    @GetMapping("/report/department")
//    public String generateReportDepartment(Model model) {
//
//        model.addAttribute("departments", departments);
//        return "department";  // Thymeleaf template name
//    }
//
//
//    @GetMapping("/report/consolidated")
//    public String getConsolidatedReport(Model model) {
//
//
//        model.addAttribute("employees", employees);
//        model.addAttribute("departments", departments);
//
//        model.addAttribute("totalEmployees", 200);
//        model.addAttribute("totalDepartments", 5);
//
//        return "consolidated";
//    }
//}