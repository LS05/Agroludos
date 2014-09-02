package agroludos.presentation.controller.mapper;

import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

public interface CommandProcessor{	
	public Object execute() throws Throwable;
	public AgroResponseContext invoke(Command command, AgroRequestContext request) throws Throwable;
}
