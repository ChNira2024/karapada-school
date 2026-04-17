package com.karapada.school.dto;

import lombok.Data;

@Data
public class NotificationRequest {
    public NotificationRequest(String email, String string, String string2) {
	}
	private String to;
    private String subject;
    private String message;
	public NotificationRequest() {
		super();
	}
    
    
}
