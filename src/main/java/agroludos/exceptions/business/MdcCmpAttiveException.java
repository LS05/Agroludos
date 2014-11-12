package agroludos.exceptions.business;


@SuppressWarnings("serial")
public class MdcCmpAttiveException extends ValidationException {

	public MdcCmpAttiveException(){
		super("Non è possibile modificare il manager di competizione in quanto gestisce competizioni attive!");
	}

	public MdcCmpAttiveException(String message){
		super(message);
	}
	
	public MdcCmpAttiveException(String message, Throwable cause){
		super(message, cause);
	}
	
	public MdcCmpAttiveException(Throwable cause){
		super(cause);
	}


}
