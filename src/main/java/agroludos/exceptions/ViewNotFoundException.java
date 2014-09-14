package agroludos.exceptions;

@SuppressWarnings("serial")
public class ViewNotFoundException extends AgroludosException{
	
	public ViewNotFoundException(String msg){
		super(msg);
	}
	
	public ViewNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
}
