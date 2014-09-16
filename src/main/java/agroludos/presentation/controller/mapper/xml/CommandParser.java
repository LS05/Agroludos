package agroludos.presentation.controller.mapper.xml;

import agroludos.exceptions.CommandFactoryException;

public interface CommandParser {

	String getClassName(String commandName) throws CommandFactoryException;
	
	String getSuccView(String commandName) throws CommandFactoryException;

	String getFailView(String commandName) throws CommandFactoryException;

}