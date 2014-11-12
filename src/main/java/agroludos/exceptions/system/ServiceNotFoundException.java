package agroludos.exceptions.system;

@SuppressWarnings("serial")
public class ServiceNotFoundException extends Exception {
	
	public ServiceNotFoundException(){
		
	}

	public ServiceNotFoundException(String message){
		super(message);
	}
	
	public ServiceNotFoundException(String message, Throwable cause){
		super("Servizio " + message + " non presente!", cause);
	}
	
	public ServiceNotFoundException(Throwable cause){
		super(cause);
	}
}
