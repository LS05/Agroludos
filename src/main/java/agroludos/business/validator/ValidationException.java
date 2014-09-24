package agroludos.business.validator;

@SuppressWarnings("serial")
public class ValidationException extends Exception {

	public ValidationException(String msg){
		super(msg);
	}

}