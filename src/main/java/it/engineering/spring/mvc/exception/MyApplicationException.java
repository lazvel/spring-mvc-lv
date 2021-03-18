package it.engineering.spring.mvc.exception;

public class MyApplicationException extends Exception{

	private static final long serialVersionUID = 17032021083400L;

	public MyApplicationException(String message) {
		super(message);
	}
}
