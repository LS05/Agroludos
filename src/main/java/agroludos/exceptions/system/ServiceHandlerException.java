package agroludos.exceptions.system;

@SuppressWarnings("serial")
public class ServiceHandlerException extends Exception {
	public ServiceHandlerException(String message){
		super(message);
	}
	
	public ServiceHandlerException(String message, Throwable cause){
		super(message, cause);
	}
	
	public ServiceHandlerException(Throwable cause){
		super(cause);
	}
}