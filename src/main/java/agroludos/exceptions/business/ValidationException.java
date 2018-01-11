package agroludos.exceptions.business;

import agroludos.to.ErrorTO;

@SuppressWarnings("serial")
public class ValidationException extends Exception {

	private ErrorTO errors;

	ValidationException(String msg){
		super(msg);
	}

	public ValidationException(ErrorTO err){
		this.errors = err;
	}

	public ValidationException(String message, Throwable cause){
		super(message, cause);
	}

	public ValidationException(Throwable cause){
		super(cause);
	}

	public ErrorTO getErrors(){
		return this.errors;
	}

}