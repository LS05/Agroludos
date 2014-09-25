package agroludos.to;

public interface ErrorTO {

	void addError(String name, String message);

	boolean hasErrors();

	String getError(String name);

}