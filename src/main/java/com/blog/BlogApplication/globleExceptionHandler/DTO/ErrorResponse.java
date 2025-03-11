package com.blog.BlogApplication.globleExceptionHandler.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String errorCode;
    private String userMessage;  // Short message for user
    private String developerMessage;  // Detailed message for developer
    private String timestamp;
    private String path;  // API path where error occurred

    public ErrorResponse(String errorCode, String userMessage, String developerMessage, String path) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
        this.timestamp = java.time.LocalDateTime.now().toString();
        this.path = path;
    }

}

