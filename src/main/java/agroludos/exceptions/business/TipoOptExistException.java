package agroludos.exceptions.business;


@SuppressWarnings("serial")
public class TipoOptExistException extends ValidationException {

	public TipoOptExistException(){
		super("Esiste già un tipo optional con questo nome!");
	}

	public TipoOptExistException(String message){
		super(message);
	}
	
	public TipoOptExistException(String message, Throwable cause){
		super(message, cause);
	}
	
	public TipoOptExistException(Throwable cause){
		super(cause);
	}


}
