//package com.aditya.demo.cofig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.IEngineConfiguration;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.cache.AlwaysValidCacheEntryValidity;
//import org.thymeleaf.context.ITemplateContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//import org.thymeleaf.templateresolver.StringTemplateResolver;
//import org.thymeleaf.templateresolver.TemplateResolution;
//import org.thymeleaf.templateresource.StringTemplateResource;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
//@Configuration
//public class ThymeleafConfig {
//
//    String employee = """
//<html xmlns:th="http://www.thymeleaf.org">
//<body>
//<div th:fragment="employeeFragment">
//    {historyTemplate}
//    <h2>Employee Report</h2>
//    <table border="1">
//        <tr>
//            <th>ID</th><th>Name</th><th>Department</th>
//        </tr>
//        <tr th:each="emp : ${employees}">
//            <td th:text="${emp.name}"></td>
//            <td th:text="${emp.role}"></td>
//            <td th:text="${emp.salary}"></td>
//        </tr>
//    </table>
//</div>
//</body>
//</html>
//""".strip();
//
//
//    String department = """
//<div th:fragment="departmentFragment">
//     <h2>Department Report</h2>
//     <table border="1">
//         <tr><th>Name</th><th>Capacity</th></tr>
//         <tr th:each="dpt : ${departments}">
//             <td th:text="${dpt.name}"></td>
//             <td th:text="${dpt.capacity}"></td>
//         </tr>
//     </table>
//</div>
//""".strip();
//
//
//    String consolidated = """
//<html xmlns:th="http://www.thymeleaf.org">
//<head>
//    <meta charset="UTF-8"/>
//    <title>Consolidated Report</title>
//</head>
//<body>
//    <h1>Consolidated Report</h1>
//    <div th:insert="employee :: employeeFragment"></div>
//    <div>
//        <h3>Summary Information</h3>
//        <p>Total Employees: <span th:text="${totalEmployees}"></span></p>
//        <p>Total Departments: <span th:text="${totalDepartments}"></span></p>
//    </div>
//    <div th:insert="department :: departmentFragment"></div>
//</body>
//</html>
//""".strip();
//
//
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//
//        StringTemplateResolver stringResolver = new StringTemplateResolver();
//        stringResolver.setTemplateMode("HTML");
//        stringResolver.setCheckExistence(true);
//
//        engine.addTemplateResolver(stringResolver);
//
//        return engine;
//    }
//
//    @Bean
//    public ITemplateResolver inMemoryTemplateResolver() {
//        return new ITemplateResolver() {
//
//            @Override
//            public String getName() {
//                return "inMemory";
//            }
//
//            @Override
//            public Integer getOrder() {
//                return 1; // High priority
//            }
//
//            @Override
//            public TemplateResolution resolveTemplate(
//                    IEngineConfiguration configuration,
//                    String ownerTemplate,
//                    String template,
//                    Map<String, Object> templateResolutionAttributes) {
//
//                String content = switch (template) {
//                    case "employee" -> employee;           // your string variable
//                    case "department" -> department;
//                    case "consolidated" -> consolidated;
//                    default -> null;
//                };
//
//                if (content == null) return null;
//
//                return new TemplateResolution(
//                        new StringTemplateResource(content),
//                        TemplateMode.HTML,
//                        AlwaysValidCacheEntryValidity.INSTANCE // make sure to import this correctly
//                );
//            }
//        };
//    }
//}
