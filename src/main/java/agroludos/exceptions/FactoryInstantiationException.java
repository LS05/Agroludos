package agroludos.exceptions;

@SuppressWarnings("serial")
public class FactoryInstantiationException extends AgroludosException{
	public FactoryInstantiationException(String msg){
		super(msg);
	}
	public FactoryInstantiationException(Exception e){
		super(e.getMessage());
	}
}
