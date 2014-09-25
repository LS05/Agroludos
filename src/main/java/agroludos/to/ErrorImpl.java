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

	@Override
	public String getError(String name) {
		String res = "";

		if(mapErrors.containsKey(name))
			res = this.mapErrors.get(name);

		return res;
	}

	@Override
	public boolean hasErrors() {
		return (errorCounts > 0) ? true : false;
	}
}