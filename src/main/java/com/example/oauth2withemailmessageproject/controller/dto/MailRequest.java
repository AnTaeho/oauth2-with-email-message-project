package com.example.oauth2withemailmessageproject.controller.dto;

import lombok.Data;

@Data
public class MailRequest {
    private String address;
    private String title;
    private String message;
}
