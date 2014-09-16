package agroludos.exceptions;

public class CommandFactoryException extends Exception {
	public CommandFactoryException(String message) {
		super(message);
	}

	public CommandFactoryException(String message, Throwable cause) {
		super(message, cause);
	}
}
