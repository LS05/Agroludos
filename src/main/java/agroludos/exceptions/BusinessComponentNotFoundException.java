package agroludos.exceptions;

@SuppressWarnings("serial")
public class BusinessComponentNotFoundException extends Exception {
	public BusinessComponentNotFoundException(String msg){
		super(msg);
	}
	
	public BusinessComponentNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
	
	public BusinessComponentNotFoundException(Throwable cause){
		super(cause);
	}
}
