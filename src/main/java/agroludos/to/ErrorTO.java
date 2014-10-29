package agroludos.to;

import java.util.Set;

public interface ErrorTO extends AgroludosTO{

	void addError(String id, String message);

	String getError(String id);
	
	Set<String> getErrors();
	
	boolean hasErrors();
	
	boolean hasError(String id);

}