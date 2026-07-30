package com.free.archecode.shared.common.exceptions.project;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
public class UserHasTooManyProjects extends  RuntimeException {
    public UserHasTooManyProjects() {
        super("You have too many projects.");
    }
}
