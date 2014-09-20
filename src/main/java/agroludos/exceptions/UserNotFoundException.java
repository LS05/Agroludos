package agroludos.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	
	public UserNotFoundException(){
		super("Utente Inesistente!");
	}

	public UserNotFoundException(String message){
		super(message);
	}
	
	public UserNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
	
	public UserNotFoundException(Throwable cause){
		super(cause);
	}
}