package agroludos.presentation.reqresh;

import java.util.Map;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataFieldException;

public class DataRequestContext implements AgroRequestContext{
	protected AgroRequest richiesta;
	protected boolean param;
	
	public void initialize(AgroRequest request) {
		this.richiesta = request;
		this.param = request.isParameter();
	}
	
	public boolean isParam(){
		return this.param;
	}
	
	@Override
	public String getCommand() {
		return this.richiesta.getCommand();
	}

	@Override
	public Object getData(Object key) throws DataFieldException{
			return this.richiesta.getData(key);
	}

	public String getStringData(Object key) throws DataFieldException{
		return this.richiesta.getData(key).toString();
	}

	@Override
	public Map<String, String> getMainData() {
		return (Map<String, String>)this.richiesta.getData();
	}

	@Override
	public String getClassName() {
		return this.getClass().getSimpleName();
	}
}
