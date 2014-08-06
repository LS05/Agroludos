package agroludos.presentation.reqresh;

import java.util.Map;

import agroludos.presentation.req.DataFieldException;

public interface AgroRequestContext {
	
	public String getCommand();
	
	public String getStringData(Object key) throws DataFieldException;
	
	public Object getData(Object key) throws DataFieldException;
	
	public Map<String, String> getMainData();
	
	public String getClassName();
	
	public boolean isParam();
}
