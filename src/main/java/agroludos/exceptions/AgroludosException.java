package agroludos.exceptions;

@SuppressWarnings("serial")
public class AgroludosException extends Exception{

	public AgroludosException(String message) {
		super(message);
	}

	public AgroludosException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AgroludosException(Throwable cause){
		super(cause);
	}
}
