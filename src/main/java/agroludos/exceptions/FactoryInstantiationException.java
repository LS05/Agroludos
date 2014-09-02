package agroludos.exceptions;

@SuppressWarnings("serial")
public class FactoryInstantiationException extends AgroRuntimeException{
	public FactoryInstantiationException(String msg){
		super(msg);
	}
	public FactoryInstantiationException(Exception e){
		super(e.getMessage());
	}
}
