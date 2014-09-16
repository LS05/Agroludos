package agroludos.exceptions;

@SuppressWarnings("serial")
public class BusinessDelegateException extends Exception {
	public BusinessDelegateException(String msg){
		super(msg);
	}
	
	public BusinessDelegateException(String msg, Throwable cause){
		super(msg, cause);
	}
	
	public BusinessDelegateException(Throwable cause){
		super(cause);
	}
}
