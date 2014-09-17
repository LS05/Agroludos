package agroludos.exceptions;

@SuppressWarnings("serial")
public class CommandFactoryException extends Exception {
	
	public CommandFactoryException() {
		super("Errore: Servizio richiesto non presente! - Controllare il file CommandFactory.xml");
	}
	
	public CommandFactoryException(String message) {
		super(message);
	}

	public CommandFactoryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CommandFactoryException(Throwable cause) {
		super(cause);
	}
}
