package agroludos.exceptions;

@SuppressWarnings("serial")
public class UserExistsException extends ValidationException {
	
	public UserExistsException(){
		super("Combinazione Username e Email già presente!");
	}

	public UserExistsException(String message){
		super(message);
	}
	
	public UserExistsException(String message, Throwable cause){
		super(message, cause);
	}
	
	public UserExistsException(Throwable cause){
		super(cause);
	}
}