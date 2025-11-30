//package com.aditya.demo.service;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.IEngineConfiguration;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresource.StringTemplateResource;
//import org.thymeleaf.templateresolver.TemplateResolution;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class TemplateService {
//
//    private final TemplateEngine templateEngine;
//    private final Map<String, String> templates = new HashMap<>();
//
//    public TemplateService(TemplateEngine templateEngine) {
//        this.templateEngine = templateEngine;
//    }
//
//    @PostConstruct
//    public void init() {
//        // Employee fragment
//        String employee = """
//                <div th:fragment="employeeFragment">
//                    <h2>Employee Report</h2>
//                    <table border="1">
//                        <tr><th>Name</th><th>Role</th><th>Salary</th></tr>
//                        <tr th:each="emp : ${employees}">
//                            <td th:text="${emp.name}"></td>
//                            <td th:text="${emp.role}"></td>
//                            <td th:text="${emp.salary}"></td>
//                        </tr>
//                    </table>
//                </div>
//                """;
//
//        // Department fragment
//        String department = """
//                <div th:fragment="departmentFragment">
//                    <h2>Department Report</h2>
//                    <table border="1">
//                        <tr><th>Name</th><th>Capacity</th></tr>
//                        <tr th:each="dpt : ${departments}">
//                            <td th:text="${dpt.name}"></td>
//                            <td th:text="${dpt.capacity}"></td>
//                        </tr>
//                    </table>
//                </div>
//                """;
//
//        // Consolidated template
//        String consolidated = """
//                <!DOCTYPE html>
//                <html xmlns:th="http://www.thymeleaf.org">
//                <body>
//                    <h1>Consolidated Report</h1>
//                    <div th:insert="employee :: employeeFragment"></div>
//                    <div>
//                        <h3>Summary Information</h3>
//                        <p>Total Employees: <span th:text="${totalEmployees}"></span></p>
//                        <p>Total Departments: <span th:text="${totalDepartments}"></span></p>
//                    </div>
//                    <div th:insert="department :: departmentFragment"></div>
//                </body>
//                </html>
//                """;
//
//        // Add templates to map
//        templates.put("employee", employee);
//        templates.put("department", department);
//        templates.put("consolidated", consolidated);
//
//        // Register custom in-memory resolver
//        ITemplateResolver resolver = new ITemplateResolver() {
//            @Override
//            public String getName() {
//                return "inMemoryResolver";
//            }
//
//            @Override
//            public Integer getOrder() {
//                return 1;
//            }
//
//            @Override
//            public TemplateResolution resolveTemplate(org.thymeleaf.templateresolver.ITemplateResolutionAttributes attributes) {
//                String templateContent = templates.get(attributes.getTemplate());
//                return new TemplateResolution(
//                        new StringTemplateResource(templateContent),
//                        TemplateMode.HTML,
//                        true
//                );
//            }
//        };
//
//        templateEngine.setTemplateResolver(resolver);
//    }
//
//    public String render(String templateName, Map<String, Object> variables) {
//        var context = new org.thymeleaf.context.Context();
//        context.setVariables(variables);
//        return templateEngine.process(templateName, context);
//    }
//}
