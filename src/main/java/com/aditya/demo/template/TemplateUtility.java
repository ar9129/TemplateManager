package com.aditya.demo.template;


import com.aditya.demo.constants.TemplateConstants;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;


@Component
public class TemplateUtility {
    public List<String> discoverPlaceHolders(String template) {
        if(template == null || template.isEmpty()){
            return List.of();
        }
        Matcher matcher = TemplateConstants.PLACEHOLDER_REGEX.matcher(template);
        return matcher.results()
                .map(match -> match.group(1))
                .collect(Collectors.toList());
    }

}