package agroludos.exceptions;

@SuppressWarnings("serial")
public class MdsNotFoundException extends Exception {

	public MdsNotFoundException(String message){
		super(message);
	}
	
	public MdsNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
	
	public MdsNotFoundException(Throwable cause){
		super(cause);
	}
}
