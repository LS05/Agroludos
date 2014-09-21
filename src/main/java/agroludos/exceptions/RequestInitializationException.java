package agroludos.exceptions;

@SuppressWarnings("serial")
public class RequestInitializationException extends Exception {
	
	public RequestInitializationException(){
		super("Nessuna informazione riguardante la richiesta! Nome del servizio da eseguire o del richiedente non impostati! Ricontrollare la strategia di creazione della richiesta.");
	}
	
	public RequestInitializationException(String msg){
		super(msg);
	}
	
	public RequestInitializationException(String msg, Throwable cause){
		super(msg, cause);
	}
}