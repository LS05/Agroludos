package agroludos.presentation.controller.mapper;

import agroludos.exceptions.BusinessComponentNotFoundException;

public interface CommandFactory {
	public Command getCommand(String commandName) throws BusinessComponentNotFoundException;
}
