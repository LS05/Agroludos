package agroludos.exceptions;

@SuppressWarnings("serial")
public class ChiuseIscrizioniException extends ValidationException {

	public ChiuseIscrizioniException(){
		super("Le iscrizioni sono chiuse!");
	}

	public ChiuseIscrizioniException(String message){
		super(message);
	}
	
	public ChiuseIscrizioniException(String message, Throwable cause){
		super(message, cause);
	}
	
	public ChiuseIscrizioniException(Throwable cause){
		super(cause);
	}

}
