package com.store.management.exception;

public class NotExistException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;
	

	public NotExistException() {
		super();
		this.message="Not Found";
		//TODO Auto-generated constructor stub
	}



	public NotExistException(String message) {
		super(message);
		this.message=message;
	}

}
