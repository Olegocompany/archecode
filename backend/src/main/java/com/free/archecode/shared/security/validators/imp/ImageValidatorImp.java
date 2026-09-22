package com.free.archecode.shared.security.validators.imp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.free.archecode.shared.common.ImageTypeUtils;
import com.free.archecode.shared.security.validators.ImageValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Реализация валидатора {@link ImageValidator}.
 * Используется {@link ImageTypeUtils}
 */

@Slf4j
public class ImageValidatorImp implements ConstraintValidator<ImageValidator, MultipartFile> {

    private static final Map<String, String> CONTENT_TYPE_TO_EXT = Map.of(
        "image/jpeg", "jpeg",
        "image/jpeg", "jpg",
        "image/png", "png",
        "image/webp", "webp"
    );

    private String[] allowedExtensions;

    @Override
    public void initialize(ImageValidator constraintAnnotation) {
        this.allowedExtensions = Arrays.stream(constraintAnnotation.value().split(","))
            .map(String::trim)
            .map(String::toLowerCase)
            .toArray(String[]::new);
        log.debug("ImageValidator initialized with extensions: {}", String.join(", ", this.allowedExtensions));
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true;
        }

        // проверка что расширение файла соответствует позволенному
        String filenameExt = extractExtension(file.getOriginalFilename());
        if (filenameExt == null || !Arrays.asList(allowedExtensions).contains(filenameExt)) {
            return fail(context, "File extension '" + filenameExt + "' is not allowed. Allowed: "
                + String.join(", ", allowedExtensions));
        }

        //
        String magicExt = detectExtension(file);
        if (magicExt == null) {
            return fail(context, "Could not detect image format from file content");
        }
        if (!sameFormat(magicExt, filenameExt)) {
            return fail(context, "File content is '" + magicExt + "' but extension says '"
                + filenameExt + "'");
        }

        String contentType = file.getContentType();
        String contentTypeExt = contentType != null ? CONTENT_TYPE_TO_EXT.get(contentType.toLowerCase()) : null;
        if (contentTypeExt == null) {
            return fail(context, "Missing or unsupported Content-Type: " + contentType);
        }
        if (!sameFormat(contentTypeExt, filenameExt)) {
            return fail(context, "Content-Type says '" + contentTypeExt + "' but extension says '"
                + filenameExt + "'");
        }

        return true;
    }

    private String extractExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int dot = filename.lastIndexOf('.');
        if (dot < 0 || dot == filename.length() - 1) {
            return null;
        }
        return filename.substring(dot + 1).toLowerCase();
    }

    private boolean fail(ConstraintValidatorContext context, String message) {
        log.warn("Image validation failed: {}", message);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }

    /**
    * give {@link ImageTypeUtils} first 12 bytes to check extenison
    * @param file
    * @return String extension
 */
    private String detectExtension(MultipartFile file) {
        try {
            return ImageTypeUtils.detectExtension(file.getInputStream().readNBytes(12));
        } catch (IOException e) {
            log.error("Failed to read file header for image validation", e);
        }
        return null;
    }

    private boolean sameFormat(String a, String b) {
        return a.equals(b) || (isJpegFamily(a) && isJpegFamily(b));
    }

    private boolean isJpegFamily(String ext) {
        return "jpg".equals(ext) || "jpeg".equals(ext);
    }
}