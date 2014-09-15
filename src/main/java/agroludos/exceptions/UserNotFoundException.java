package agroludos.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String msg){
		super(msg);
	}
	
	public UserNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
	
	public UserNotFoundException(Throwable cause){
		super(cause);
	}
}