package com.aditya.demo.template;

public final class TemplateContents {

    private TemplateContents() {}

    public static final String EMPLOYEE_REPORT = """
        <div th:fragment="employeeFragment">
            <h2>Employee Report</h2>
            <table border="1" style="width:100%;">
                <tr style="background-color: #f2f2f2;">
                    <th>Name</th><th>Role</th><th th:if="${!isConsolidated}">Salary</th>
                </tr>
                <tr th:each="emp : ${employees}">
                    <td th:text="${emp.name}"></td>
                    <td th:text="${emp.role}"></td>
                    <td th:if="${!isConsolidated}" th:text="${emp.salary}"></td>
                </tr>
            </table>
            {{historyReport}}
            {{historyReport}}
        </div>
        """;

    public static final String DEPARTMENT_REPORT = """
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
        """;

    public static final String HISTORY_REPORT = """
        <div th:fragment="historyFragment">
            <h3 style="color: blue;">Employee History Section</h3>
            <p>This is dynamic history content for Employee ID <span th:text="${employeeId}"></span>.</p>
        </div>
        """;

    public static final String CONSOLIDATED_MASTER = """
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
            {{employeeReport}}
            <hr style="margin: 30px 0;"></hr>
            {{departmentReport}}
        </body>
        </html>
        """;
}

