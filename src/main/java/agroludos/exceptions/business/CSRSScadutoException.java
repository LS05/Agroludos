package agroludos.exceptions.business;


@SuppressWarnings("serial")
public class CSRSScadutoException extends ValidationException {

	public CSRSScadutoException(){
		super("Certificato Sana e Robusta Costituzione scaduto!");
	}

	public CSRSScadutoException(String message){
		super(message);
	}
	
	public CSRSScadutoException(String message, Throwable cause){
		super(message, cause);
	}
	
	public CSRSScadutoException(Throwable cause){
		super(cause);
	}

}
