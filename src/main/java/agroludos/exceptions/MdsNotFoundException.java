package agroludos.exceptions;

@SuppressWarnings("serial")
public class MdsNotFoundException extends Exception {

	public MdsNotFoundException(){
		super("Attenzione Manager Di Sistema non trovato,"
				+ " è necessario inserirlo nuovamente!");
	}

	public MdsNotFoundException(String message){
		super(message);
	}

	public MdsNotFoundException(String message, Throwable cause){
		super(message, cause);
	}

	public MdsNotFoundException(Throwable cause){
		super(cause);
	}
}