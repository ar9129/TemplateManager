package com.aditya.demo.context;

import com.aditya.demo.model.Department;
import com.aditya.demo.model.Employee;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import java.util.List;

@Component
public class ReportContextBuilder {

    public Context buildContext() {

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

