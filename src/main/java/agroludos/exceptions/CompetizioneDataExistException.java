package agroludos.exceptions;

@SuppressWarnings("serial")
public class CompetizioneDataExistException extends ValidationException {

	public CompetizioneDataExistException(){
		super("Esiste già una competizione nella data inserita!");
	}

	public CompetizioneDataExistException(String message){
		super(message);
	}
	
	public CompetizioneDataExistException(String message, Throwable cause){
		super(message, cause);
	}
	
	public CompetizioneDataExistException(Throwable cause){
		super(cause);
	}

}
