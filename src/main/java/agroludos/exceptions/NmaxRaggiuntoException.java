package agroludos.exceptions;

@SuppressWarnings("serial")
public class NmaxRaggiuntoException extends ValidationException {
	
	public NmaxRaggiuntoException(){
		super("Numero massimo di partecipanti raggiunto!");
	}

	public NmaxRaggiuntoException(String message){
		super(message);
	}
	
	public NmaxRaggiuntoException(String message, Throwable cause){
		super(message, cause);
	}
	
	public NmaxRaggiuntoException(Throwable cause){
		super(cause);
	}
}
