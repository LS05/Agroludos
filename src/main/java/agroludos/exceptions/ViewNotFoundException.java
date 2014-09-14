package agroludos.exceptions;

@SuppressWarnings("serial")
public class ViewLoadingException extends AgroludosException{
	
	public ViewLoadingException(String msg){
		super(msg);
	}
	
	public ViewLoadingException(String message, Throwable cause){
		super(message, cause);
	}
}
