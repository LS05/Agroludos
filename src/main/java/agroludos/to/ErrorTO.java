package agroludos.to;

public interface ErrorTO extends AgroludosTO{

	void addError(String name, String message);

	boolean hasErrors();

	String getError(String name);

}