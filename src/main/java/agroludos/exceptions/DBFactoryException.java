package agroludos.exceptions;

@SuppressWarnings("serial")
public class DBFactoryException extends DatabaseException{
	
	public DBFactoryException(String message){
		super(message);
	}
	
	public DBFactoryException(String message, Throwable cause){
		super(message, cause);
	}

}