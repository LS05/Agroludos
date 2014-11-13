package agroludos.exceptions.business;


@SuppressWarnings("serial")
public class CSRCScadutoException extends ValidationException {

	public CSRCScadutoException(){
		super("Certificato Sana e Robusta Costituzione scaduto!\n Aggiornarlo.");
	}

	public CSRCScadutoException(String message){
		super(message);
	}
	
	public CSRCScadutoException(String message, Throwable cause){
		super(message, cause);
	}
	
	public CSRCScadutoException(Throwable cause){
		super(cause);
	}

}
