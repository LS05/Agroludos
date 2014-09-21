package agroludos.exceptions;

@SuppressWarnings("serial")
public class MethodNotFoundException extends Exception {
	
	public MethodNotFoundException(){
		super();
	}
	
	public MethodNotFoundException(String message){
		super(message);
	}
	
	public MethodNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
	
	public MethodNotFoundException(Throwable cause){
		super(cause);
	}
}
