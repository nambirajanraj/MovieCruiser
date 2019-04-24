package com.stackroute.moviecrusierserverapp.exception;


@SuppressWarnings("serial")
public class MovieAlreadyExistsException extends Exception {
	
	String message;

	@Override
	public String toString() {
		return "MovieAlreadyExitsException [message=" + message + "]";
	}

	public MovieAlreadyExistsException(final String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
