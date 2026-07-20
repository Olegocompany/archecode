package com.free.archecode.shared.common.exceptions.project;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CantFindGitProjectException extends RuntimeException  {
    public CantFindGitProjectException(String message) {
        super(message);
    }
}
