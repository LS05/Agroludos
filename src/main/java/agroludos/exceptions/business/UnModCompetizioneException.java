package agroludos.exceptions.business;


@SuppressWarnings("serial")
public class UnModCompetizioneException extends ValidationException {

	public UnModCompetizioneException(){
		super("Non è possibile modificare una competizione non aperta alle iscrizioni!");
	}

	public UnModCompetizioneException(String message){
		super(message);
	}
	
	public UnModCompetizioneException(String message, Throwable cause){
		super(message, cause);
	}
	
	public UnModCompetizioneException(Throwable cause){
		super(cause);
	}

}
