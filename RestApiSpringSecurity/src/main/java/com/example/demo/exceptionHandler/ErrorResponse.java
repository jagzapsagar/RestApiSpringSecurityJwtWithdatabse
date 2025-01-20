package com.example.demo.exceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorResponse  {
	
	private String timestamp;
    private String message;
    private String details;
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(String timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String localDate) {
		this.timestamp = localDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
    
    

}
