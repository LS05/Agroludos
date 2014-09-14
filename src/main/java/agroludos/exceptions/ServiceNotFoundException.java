package agroludos.exceptions;

@SuppressWarnings("serial")
public class ServiceNotFoundException extends AgroludosException {

	public ServiceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceNotFoundException(String message) {
		super(message);
	}

}
