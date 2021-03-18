package it.engineering.spring.mvc.exception;

public class ExistEntityException extends MyApplicationException{
	private static final long serialVersionUID = 17032021083500L;
	private final Object entity;
	
	public ExistEntityException(Object entity, String message) {
		super(message);
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}
	
}
