package agroludos.presentation.controller.mapper;

import agroludos.exceptions.ApplicationException;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

public interface CommandProcessor{	
	public AgroResponseContext invoke(Command command, AgroRequestContext request) throws ApplicationException;
}
