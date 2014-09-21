package agroludos.presentation.controller.mapper.xml;

import agroludos.exceptions.CommandFactoryException;

public interface CommandParser {

	String getClassName(String commandName) throws CommandFactoryException;
	
	public String getFailView(String commandName, String fromName) throws CommandFactoryException;
	
	public String getSuccView(String commandName, String fromName) throws CommandFactoryException;

}