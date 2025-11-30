package com.aditya.demo.utility;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Component
public class TemplateParserUtility {
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{\\{(\\w+)}}");

    public List<String> discoverPlaceHolders(String template) {
        if(template == null || template.isEmpty()){
            return List.of();
        }
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
        return matcher.results()
                .map(match -> match.group(1))
                .collect(Collectors.toList());
    }

}