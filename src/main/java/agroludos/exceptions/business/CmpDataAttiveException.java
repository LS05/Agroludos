package agroludos.exceptions.business;


@SuppressWarnings("serial")
public class CmpDataAttiveException extends ValidationException {

	public CmpDataAttiveException(){
		super("Esiste già una competizione nella data inserita!");
	}

	public CmpDataAttiveException(String message){
		super(message);
	}
	
	public CmpDataAttiveException(String message, Throwable cause){
		super(message, cause);
	}
	
	public CmpDataAttiveException(Throwable cause){
		super(cause);
	}

}
