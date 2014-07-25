package agroludos.presentation.reqresh;

import java.util.Map;

import adisys.server.presentation.req.AdiRequest;
import adisys.server.presentation.req.DataFieldException;

public abstract class AdiRequestContext {
	protected AdiRequest richiesta;
	protected boolean param;
	
	public void initialize(AdiRequest request) {
		this.richiesta = request;
		this.param = request.isParameter();
	}
	
	public boolean isParam(){
		return this.param;
	}
	
	public abstract String getCommand();
	
	public abstract String getStringData(Object key) throws DataFieldException;
	
	public abstract Object getData(Object key) throws DataFieldException;
	
	public abstract Map<String, String> getMainData();
}
