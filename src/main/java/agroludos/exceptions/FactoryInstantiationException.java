package agroludos.exceptions;

@SuppressWarnings("serial")
public class FactoryInstantiationException extends Exception{
	
	public FactoryInstantiationException(String message){
		super(message);
	}
	
	public FactoryInstantiationException(String message, Throwable cause){
		super(message, cause);
	}
	
	public FactoryInstantiationException(Throwable cause){
		super(cause);
	}
}
