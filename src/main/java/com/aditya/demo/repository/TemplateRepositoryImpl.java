package com.aditya.demo.repository;

import com.aditya.demo.model.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TemplateRepositoryImpl implements TemplateRepository {
    @Override
    public List<TemplateEntity> findAll() {
        return List.of(
//                new TemplateEntity("departmentReport", "<div th:fragment=\"departmentFragment\">...</div>"),
//                new TemplateEntity("historyReport", "<div th:fragment=\"historyFragment\">...</div>"),
//                new TemplateEntity("employeeReport", "<div th:fragment=\"employeeFragment\">{{historyReport}}...</div>"),
//                new TemplateEntity("CONSOLIDATED_MASTER", "<!DOCTYPE html>...{{employeeReport}}<hr>{{departmentReport}}..."),
//                new TemplateEntity("L3_ReportA", "Template L3A content"),
//                new TemplateEntity("L2_Section1", "Section 1 includes: {{L3_ReportA}}")
//        );
//    }
//}


                new TemplateEntity("employeeReport", """
                        <div th:fragment="employeeFragment">
//                            [[historyTemplate]]
                            {{historyTemplate}}
                            <h2>Employee Report</h2>
                            <table border="1" style="width:100%;">
                                <tr style="background-color: #f2f2f2;">
                                    <th>Name</th><th>Role</th><th>Salary</th>
                                </tr>
                                <tr th:each="emp : ${employees}">
                                    <td th:text="${emp.name}"></td>
                                    <td th:text="${emp.role}"></td>
                                    <td th:text="${emp.salary}"></td>
                                </tr>
                            </table>
                        </div>
                        """),

                new TemplateEntity("departmentReport", """
                        <div th:fragment="departmentFragment">
                            <h2>Department Report</h2>
                            <table border="1" style="width:100%;">
                                <tr style="background-color: #f2f2f2;">
                                    <th>Name</th><th>Capacity</th>
                                </tr>
                                <tr th:each="dpt : ${departments}">
                                    <td th:text="${dpt.name}"></td>
                                    <td th:text="${dpt.capacity}"></td>
                                </tr>
                            </table>
                        </div>
                        """),

                new TemplateEntity("historyReport", """
                        <div th:fragment="historyFragment">
                            <h3 style="color: blue;">Employee History Section</h3>
                            <p>This is dynamic history content for Employee ID <span th:text="${employeeId}"></span>.</p>
                        </div>
                        """),


                new TemplateEntity("CONSOLIDATED_MASTER", """
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <title>Consolidated Report</title>
                            <style>
                                body { font-family: Arial, sans-serif; }
                                table { border-collapse: collapse; margin-bottom: 20px; }
                                th, td { border: 1px solid #ddd; padding: 8px; }
                            </style>
                        </head>
                        <body>
                            <h1 style="text-align: center;">-- Consolidated Report --</h1>
                            <p>Report Date: <span th:text="${reportDate}"></span></p>
                        
//                            [[employeeReport]]
                            {{employeeReport}}
                        
                            <hr style="margin: 30px 0;"></hr>
                        
//                            [[departmentReport]]
                            {{departmentReport}}
                        
                        </body>
                        </html>
                        """)
        );
    }
}
