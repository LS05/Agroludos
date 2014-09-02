package agroludos.presentation.reqh;

import agroludos.presentation.req.DataFieldException;

public interface DataRequestContext extends AgroRequestContext{
	
	public String getStringData(Object key) throws DataFieldException;
	public Object getData(Object key) throws DataFieldException;
	
}
