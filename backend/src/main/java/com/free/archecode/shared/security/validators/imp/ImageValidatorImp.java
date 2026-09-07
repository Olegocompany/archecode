package com.free.archecode.shared.security.validators.imp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.free.archecode.shared.security.validators.ImageValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Проверка начальных байтов файла.
 * На основании этих байтов делаем вывод кто есть кто.
 * Также, проверяется 
 * ImageValidatorImp
 */

@Slf4j
public class ImageValidatorImp implements ConstraintValidator<ImageValidator, MultipartFile> {

    // биты, с которых идет начало реальных изображений, а не просто проверка расширения
    private static final Map<String, byte[]> MAGIC_BYTES = Map.of(
        "jpg",  new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF},
        "jpeg",  new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF},
        "png",  new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47},
        "webp", new byte[]{0x52, 0x49, 0x46, 0x46} // RIFF header, check offset 8 for "WEBP"
    );

    private static final byte[] WEBP_SIGNATURE = {0x57, 0x45, 0x42, 0x50}; // WEBP at offset 8

    private static final Map<String, String> CONTENT_TYPE_TO_EXT = Map.of(
        "image/jpeg", "jpeg",
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

        String filenameExt = extractExtension(file.getOriginalFilename());
        if (filenameExt == null || !Arrays.asList(allowedExtensions).contains(filenameExt)) {
            return fail(context, "File extension '" + filenameExt + "' is not allowed. Allowed: "
                + String.join(", ", allowedExtensions));
        }

        String magicExt = detectExtension(file);
        if (magicExt == null) {
            return fail(context, "Could not detect image format from file content");
        }
        if (!magicExt.equals(filenameExt)) {
            return fail(context, "File content is '" + magicExt + "' but extension says '"
                + filenameExt + "'");
        }

        String contentType = file.getContentType();
        String contentTypeExt = contentType != null ? CONTENT_TYPE_TO_EXT.get(contentType.toLowerCase()) : null;
        if (contentTypeExt == null) {
            return fail(context, "Missing or unsupported Content-Type: " + contentType);
        }
        if (!contentTypeExt.equals(filenameExt)) {
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

    private String detectExtension(MultipartFile file) {
        try {
            byte[] header = file.getInputStream().readNBytes(12);

            for (Map.Entry<String, byte[]> entry : MAGIC_BYTES.entrySet()) {
                String ext = entry.getKey();
                byte[] magic = entry.getValue();

                if (matchesHeader(header, magic)) {
                    if ("webp".equals(ext)) {
                        return isWebP(header) ? "webp" : null;
                    }
                    return ext;
                }
            }
        } catch (IOException e) {
            log.error("Failed to read file header for image validation", e);
        }
        return null;
    }

    private boolean matchesHeader(byte[] header, byte[] magic) {
        if (header.length < magic.length) {
            return false;
        }
        for (int i = 0; i < magic.length; i++) {
            if (header[i] != magic[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isWebP(byte[] header) {
        if (header.length < 12) {
            return false;
        }
        return header[8] == WEBP_SIGNATURE[0] &&
               header[9] == WEBP_SIGNATURE[1] &&
               header[10] == WEBP_SIGNATURE[2] &&
               header[11] == WEBP_SIGNATURE[3];
    }
}
