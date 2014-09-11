package agroludos.presentation.controller.mapper.xml;

import agroludos.exceptions.ServiceNotFoundException;

public interface CommandParser {

	String getClassName(String commandName) throws ServiceNotFoundException;
	
	String getSuccView(String commandName) throws ServiceNotFoundException;

	String getFailView(String commandName) throws ServiceNotFoundException;

}