package com.aditya.demo.model;

public class TemplateEntity {
    private String templateName;
    private String templateContent;

    public TemplateEntity(String templateName, String templateContent) {
        this.templateName = templateName;
        this.templateContent = templateContent;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }
}
