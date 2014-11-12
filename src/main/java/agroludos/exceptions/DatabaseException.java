package agroludos.exceptions;

@SuppressWarnings("serial")
public class DatabaseException extends SystemException {
	
	public DatabaseException(){
		super();
	}
	
	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DatabaseException(Throwable cause) {
		super(cause);
	}
}