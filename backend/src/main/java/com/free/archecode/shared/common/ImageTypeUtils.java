package com.free.archecode.shared.common;

import org.springframework.http.MediaType;
/**
* Утилиты, которые имеют статические методы для обнаружения расширений
* файлов с помощью чтения первых байтов.
* ImageTypeUtils
 */
public final class ImageTypeUtils {

    private static final byte[] JPEG_MAGIC = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};
    private static final byte[] PNG_MAGIC = {(byte) 0x89, 0x50, 0x4E, 0x47};
    private static final byte[] RIFF_MAGIC = {0x52, 0x49, 0x46, 0x46};
    private static final byte[] WEBP_MAGIC = {0x57, 0x45, 0x42, 0x50};
    private static final int WEBP_MAGIC_OFFSET = 8;

    private ImageTypeUtils() {
    }

    public static boolean isJpeg(byte[] data) {
        return matches(data, JPEG_MAGIC);
    }

    public static boolean isPng(byte[] data) {
        return matches(data, PNG_MAGIC);
    }

    public static boolean isWebp(byte[] data) {
        return matches(data, RIFF_MAGIC) && matchesAt(data, WEBP_MAGIC_OFFSET, WEBP_MAGIC);
    }

    public static String detectExtension(byte[] data) {
        if (isJpeg(data)) {
            return "jpeg";
        }
        if (isPng(data)) {
            return "png";
        }
        if (isWebp(data)) {
            return "webp";
        }
        return null;
    }

    public static MediaType detectMediaType(byte[] data) {
        return switch (detectExtension(data)) {
            case "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "webp" -> MediaType.parseMediaType("image/webp");
            default -> throw new IllegalArgumentException("Unsupported image format");
        };
    }

    // просто 2 метода для сравнений
    private static boolean matches(byte[] data, byte[] magic) {
        return data.length >= magic.length && matchesAt(data, 0, magic);
    }

    private static boolean matchesAt(byte[] data, int offset, byte[] magic) {
        if (data.length < offset + magic.length) {
            return false;
        }
        for (int i = 0; i < magic.length; i++) {
            if (data[offset + i] != magic[i]) {
                return false;
            }
        }
        return true;
    }
}