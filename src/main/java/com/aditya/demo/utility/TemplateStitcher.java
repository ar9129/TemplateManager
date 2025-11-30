package com.aditya.demo.utility;

import com.aditya.demo.model.TemplateConfig;
import com.aditya.demo.service.TemplateStorageService;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TemplateStitcher {

    private final TemplateStorageService templateStorageService;

    public TemplateStitcher(TemplateStorageService templateStorageService) {
        this.templateStorageService = templateStorageService;
    }

    public String stitchTemplate(String templateKey) {
        return stitchTemplateRecursive(templateKey, new HashSet<>());
    }

    private String stitchTemplateRecursive(String templateKey, Set<String> processingTemplates) {

        // --- Circular Dependency Check (Skip Logic) ---
        if (processingTemplates.contains(templateKey)) {
            System.out.println("⚠️ Circular Dependency Detected: Skipping recursion for " + templateKey);
            return String.format("{{%s}}", templateKey);
        }

        processingTemplates.add(templateKey);
        TemplateConfig config = templateStorageService.getTemplateConfig(templateKey);

        if (config == null || config.content() == null) {
            processingTemplates.remove(templateKey);
            return "";
        }

        String stitchedContent = config.content();
        List<String> dependencies = config.requiredTemplates();
        List<String> placeholders = config.requiredPlaceholders();

        if (dependencies.isEmpty()) {
            processingTemplates.remove(templateKey);
            return stitchedContent;
        }

        // --- FIX: Ensure lists have the same length ---
//        final int dependencySize = dependencies.size();
        final int placeholderSize = placeholders.size();
//        final int loopLimit = placeholderSize;

//        if (dependencySize != placeholderSize) {
//            // Log this error loudly, as it indicates a serious bug in the parsing utility
//            System.err.printf(
//                    "❌ WARNING: Configuration Error for '%s'. Dependencies count (%d) does not match placeholders count (%d). Using shorter length (%d) to prevent IndexOutOfBoundsException.\n",
//                    templateKey, dependencySize, placeholderSize, loopLimit
//            );
//        }

        // 3. Recursive Step: Resolve and stitch all dependencies
        for (int i = 0; i < placeholderSize; i++) {
            String dependencyKey = dependencies.get(i);
            String placeholder = placeholders.get(i);

            String resolvedDependencyContent = stitchTemplateRecursive(dependencyKey, processingTemplates);

            if (!resolvedDependencyContent.equals(placeholder)) {
                stitchedContent = stitchedContent.replace(placeholder, resolvedDependencyContent);
            } else {
                System.out.println("   --> Placeholder left unresolved in '" + templateKey + "' for dependency: " + dependencyKey);
            }
        }

        processingTemplates.remove(templateKey);
        return stitchedContent;
    }
}

//        String stitchedContent = config.content();
//        List<String> dependencies = config.requiredTemplates();
//        List<String> placeholders = config.requiredPlaceholders();
//
//        if (dependencies.isEmpty()) {
//            // Base case: No dependencies left to resolve
//            processingTemplates.remove(templateKey);
//            return stitchedContent;
//        }
//
//        // 3. Recursive Step: Resolve and stitch all dependencies
//        for (int i = 0; i < dependencies.size(); i++) {
//            String dependencyKey = dependencies.get(i);
//            String placeholder = placeholders.get(i);
//
//            // Recursive call
//            String resolvedDependencyContent = stitchTemplateRecursive(dependencyKey, processingTemplates);
//
//            // 4. Check if the resolution failed (resolved content equals the unresolved placeholder)
//            if (!resolvedDependencyContent.equals(placeholder)) {
//                // Dependency resolved successfully. Replace it.
//                stitchedContent = stitchedContent.replace(placeholder, resolvedDependencyContent);
//            } else {
//                // Dependency recursion was skipped due to circularity.
//                // We leave the placeholder in the 'stitchedContent'.
//                System.out.println("   --> Placeholder left unresolved in '" + templateKey + "' for dependency: " + dependencyKey);
//            }
//        }
//
//        // 5. Clean up: Remove the current template key before returning
//        processingTemplates.remove(templateKey);
//
//        return stitchedContent;
//    }
//}
