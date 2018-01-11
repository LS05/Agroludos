package agroludos.presentation.controller.mapper;

import agroludos.exceptions.system.CommandFactoryException;

public interface CommandFactory {
	public Command getCommand(String commandName, String viewName) throws CommandFactoryException;
}
