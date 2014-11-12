package agroludos.exceptions.system;

@SuppressWarnings("serial")
public class ViewNotFoundException extends Exception{
	
	public ViewNotFoundException(String msg){
		super(msg);
	}
	
	public ViewNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
}
