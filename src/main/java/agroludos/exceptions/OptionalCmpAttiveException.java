package agroludos.exceptions;

@SuppressWarnings("serial")
public class OptionalCmpAttiveException extends ValidationException {

	public OptionalCmpAttiveException(){
		super("Non è possibile modificare l'optional in quanto fa parte di competizioni attive!");
	}

	public OptionalCmpAttiveException(String message){
		super(message);
	}
	
	public OptionalCmpAttiveException(String message, Throwable cause){
		super(message, cause);
	}
	
	public OptionalCmpAttiveException(Throwable cause){
		super(cause);
	}

}
