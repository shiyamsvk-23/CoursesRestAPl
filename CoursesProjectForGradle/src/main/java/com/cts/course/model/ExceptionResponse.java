package com.cts.course.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
/**
 * 
 * @author 882851
 * ExceptionResponse class is used for custom exception structure
 */
public class ExceptionResponse {
	
	private String message;
	private LocalDateTime date;
	HttpStatus status;
	public ExceptionResponse() {
			
	}
	public ExceptionResponse(String message, LocalDateTime date, HttpStatus status) {
		super();
		this.message = message;
		this.date = date;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
