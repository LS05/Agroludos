package agroludos.presentation.controller.mapper;

import agroludos.exceptions.ServiceNotFoundException;

public interface CommandFactory {
	public Command getCommand(String commandName) throws ServiceNotFoundException;
}
