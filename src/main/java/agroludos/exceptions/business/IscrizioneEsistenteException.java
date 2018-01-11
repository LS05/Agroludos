package agroludos.exceptions.business;



@SuppressWarnings("serial")
public class IscrizioneEsistenteException extends ValidationException {
	
	public IscrizioneEsistenteException() {
		super("Iscrizione già esistente!");
	}

	public IscrizioneEsistenteException(String message){
		super(message);
	}
	
	public IscrizioneEsistenteException(String message, Throwable cause){
		super(message, cause);
	}
	
	public IscrizioneEsistenteException(Throwable cause){
		super(cause);
	}

}
