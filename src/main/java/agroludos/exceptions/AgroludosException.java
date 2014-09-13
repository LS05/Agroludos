package agroludos.exceptions;

@SuppressWarnings("serial")
public class AgroRuntimeException extends RuntimeException{

	public AgroRuntimeException(String message) {
		super(message);
	}

	public AgroRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
