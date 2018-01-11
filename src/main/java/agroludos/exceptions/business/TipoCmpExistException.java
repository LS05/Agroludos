package agroludos.exceptions.business;


@SuppressWarnings("serial")
public class TipoCmpExistException extends ValidationException {

	public TipoCmpExistException(){
		super("Esiste già un tipo competizione con questo nome!");
	}

	public TipoCmpExistException(String message){
		super(message);
	}
	
	public TipoCmpExistException(String message, Throwable cause){
		super(message, cause);
	}
	
	public TipoCmpExistException(Throwable cause){
		super(cause);
	}


}
