package agroludos.exceptions;

import agroludos.to.ErrorTO;

@SuppressWarnings("serial")
public class ValidationException extends Exception {

	private ErrorTO errors;

	public ValidationException(ErrorTO err){
		this.errors = err;
	}
	
	public ValidationException(String msg){
		super(msg);
	}

}