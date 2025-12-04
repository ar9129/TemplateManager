package com.aditya.demo.constants;

import java.util.regex.Pattern;

public class TemplateConstants {
    public static final Pattern PLACEHOLDER_REGEX = Pattern.compile("\\{\\{(\\w+)}}");
    public static final Integer MAX_DEPTH = 3;

    public static final String PLACEHOLDER_PREFIX = "{{";
    public static final String PLACEHOLDER_SUFFIX = "}}";

    public static String toPlaceholderLiteral(String placeholder) {
        return PLACEHOLDER_PREFIX + placeholder + PLACEHOLDER_SUFFIX;
    }
}
