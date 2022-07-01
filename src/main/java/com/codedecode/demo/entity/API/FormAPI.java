package com.codedecode.demo.entity.API;

public class FormAPI {
	private ErrorAPI error;
	private DataAPI date;
	private String message;
	public FormAPI(){
		
	}
	public ErrorAPI getError() {
		return error;
	}
	public void setError(ErrorAPI error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DataAPI getDate() {
		return date;
	}
	public void setDate(DataAPI date) {
		this.date = date;
	}
}
 
