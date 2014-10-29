package agroludos.to;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ErrorImpl implements ErrorTO{
	private static final long serialVersionUID = -2933017900423676320L;
	private int errorCounts;
	private Map<String, String> mapErrors;

	public ErrorImpl(){
		this.mapErrors = new HashMap<String, String>();
		this.errorCounts = 0;
	}

	@Override
	public void addError(String id, String message) {
		this.mapErrors.put(id, message);
		this.errorCounts++;
	}

	@Override
	public String getError(String id) {
		String res = "";

		if(this.mapErrors.containsKey(id))
			res = this.mapErrors.get(id);

		return res;
	}

	@Override
	public boolean hasErrors() {
		return (this.errorCounts > 0) ? true : false;
	}
	
	@Override
	public boolean hasError(String id) {
		return this.mapErrors.containsKey(id);
	}

	@Override
	public Set<String> getErrors() {
		return this.mapErrors.keySet();
	}
}