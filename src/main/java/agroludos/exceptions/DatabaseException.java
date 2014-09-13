package agroludos.exceptions;

@SuppressWarnings("serial")
public class DatabaseException extends AgroludosException {
	
	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
