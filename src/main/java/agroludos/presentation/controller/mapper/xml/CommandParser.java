package agroludos.presentation.controller.mapper.xml;

import agroludos.exceptions.BusinessComponentNotFoundException;

public interface CommandParser {

	String getClassName(String commandName) throws BusinessComponentNotFoundException;
	
	String getSuccView(String commandName) throws BusinessComponentNotFoundException;

	String getFailView(String commandName) throws BusinessComponentNotFoundException;

}