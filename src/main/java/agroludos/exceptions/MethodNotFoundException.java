package agroludos.exceptions;

@SuppressWarnings("serial")
public class MethodNotFoundException extends AgroRuntimeException {
	public MethodNotFoundException(String msg){
		super(msg);
	}
}
