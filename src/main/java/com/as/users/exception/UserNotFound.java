package com.as.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "invalid user")
public class UserNotFound extends RuntimeException{
    private String title;
    private String detail;
    private String type;
    public UserNotFound(String title, String detail, String type) {
        this.title = title;
        this.detail = detail;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getType() {
        return type;
    }
}
