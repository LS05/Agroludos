package agroludos.exceptions.system;

@SuppressWarnings("serial")
public class DBFactoryException extends DatabaseException{
	
	public DBFactoryException(){
		super("Tipo Database non riconosciuto.");
	}
	
	public DBFactoryException(String message){
		super(message);
	}
	
	public DBFactoryException(String message, Throwable cause){
		super(message, cause);
	}

}