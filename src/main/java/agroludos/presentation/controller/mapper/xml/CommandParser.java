package agroludos.presentation.controller.mapper.xml;

public interface CommandParser {

	String getClassName(String commandName);
	
	String getSuccView(String commandName);

	String getFailView(String commandName);

}