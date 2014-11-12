package agroludos.exceptions.system;

@SuppressWarnings("serial")
public class SystemException extends Exception {

	public SystemException(){
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

}