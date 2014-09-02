package agroludos.presentation.controller.mapper.xml;

public interface CommandParser {

	String getClassName(String commandName);

	String getFailPath(String commandName);

	String getSuccPath(String commandName);
	
	String getSuccView(String commandName);

	String getFailView(String commandName);

}