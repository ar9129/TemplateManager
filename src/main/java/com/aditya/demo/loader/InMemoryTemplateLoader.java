package com.aditya.demo.loader;

import com.aditya.demo.constants.TemplateContents;
import com.aditya.demo.constants.TemplateIds;
import com.aditya.demo.model.TemplateEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryTemplateLoader implements TemplateLoader {
    @Override
    public List<TemplateEntity> loadTemplates() {
        return List.of(
                new TemplateEntity(TemplateIds.EMPLOYEE_REPORT, TemplateContents.EMPLOYEE_REPORT),
                new TemplateEntity(TemplateIds.DEPARTMENT_REPORT, TemplateContents.DEPARTMENT_REPORT),
                new TemplateEntity(TemplateIds.HISTORY_REPORT, TemplateContents.HISTORY_REPORT),
                new TemplateEntity(TemplateIds.CONSOLIDATED_MASTER, TemplateContents.CONSOLIDATED_MASTER)
        );
    }
}
