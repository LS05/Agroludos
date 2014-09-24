package agroludos.to;

import java.util.HashMap;
import java.util.Map;

public class ErrorImpl implements ErrorTO{
	private int errorCounts;
	Map<String, String> mapErrors;
	
	public ErrorImpl(){
		this.mapErrors = new HashMap<String, String>();
		this.errorCounts = 0;
	}
	
	@Override
	public void addError(String name, String message) {
		this.mapErrors.put(name, message);
		this.errorCounts++;
	}

	boolean isError(){
		return (errorCounts > 0) ? true : false;
	}
}
