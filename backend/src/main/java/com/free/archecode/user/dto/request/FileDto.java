package com.free.archecode.user.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.free.archecode.shared.security.validators.ImageValidator;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FileDto {

    @ImageValidator("png,jpg,jpeg")
    @NotNull 
    MultipartFile file;
}
