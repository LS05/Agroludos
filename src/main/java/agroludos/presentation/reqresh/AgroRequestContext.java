package agroludos.presentation.reqresh;

import java.util.Map;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataFieldException;

public abstract class AgroRequestContext {
	protected AgroRequest richiesta;
	protected boolean param;
	
	public void initialize(AgroRequest request) {
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
