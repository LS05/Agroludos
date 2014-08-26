package agroludos.exceptions;

@SuppressWarnings("serial")
public class DBFactoryException extends RuntimeException{
	
	public DBFactoryException(String msg){
		super(msg);
	}

}