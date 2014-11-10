package agroludos.exceptions;

@SuppressWarnings("serial")
public class NoModificaCmp extends ValidationException {

	public NoModificaCmp(){
		super("Non è possibile modificare una competizione non aperta alle iscrizioni!");
	}

	public NoModificaCmp(String message){
		super(message);
	}
	
	public NoModificaCmp(String message, Throwable cause){
		super(message, cause);
	}
	
	public NoModificaCmp(Throwable cause){
		super(cause);
	}

}
