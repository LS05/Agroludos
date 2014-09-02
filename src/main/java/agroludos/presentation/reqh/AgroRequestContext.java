package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;

public interface AgroRequestContext {
	
	public void initialize(AgroRequest request);
	
	public String getCommandName();
	
	public String getClassName();
	
	public boolean isParam();
	
	public AgroRequest getRequest();
}
