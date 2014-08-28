package agroludos.exceptions;

@SuppressWarnings("serial")
public class DatabaseException extends AgroRuntimeException {
	
	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
