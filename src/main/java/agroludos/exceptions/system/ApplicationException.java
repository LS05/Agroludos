package agroludos.exceptions.system;

@SuppressWarnings("serial")
public class ApplicationException extends Exception{
	
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(Throwable cause) {
		super(cause);
	}
}