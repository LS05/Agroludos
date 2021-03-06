package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;
import agroludos.to.AgroludosTO;

public interface AgroRequestContext {
	
	public void initialize(AgroRequest request);
	
	public String getCommandName();
	
	public String getClassName();
	
	public boolean isParam();
	
	public AgroRequest getRequest();
	
	public AgroludosTO getData();

	public String getviewName();
}
