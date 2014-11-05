package agroludos.exceptions;

@SuppressWarnings("serial")
public class UtenteEsistenteException extends ValidationException {
	
	public UtenteEsistenteException(){
		super("Username e/o Email già presenti!");
	}

	public UtenteEsistenteException(String message){
		super(message);
	}
	
	public UtenteEsistenteException(String message, Throwable cause){
		super(message, cause);
	}
	
	public UtenteEsistenteException(Throwable cause){
		super(cause);
	}
}