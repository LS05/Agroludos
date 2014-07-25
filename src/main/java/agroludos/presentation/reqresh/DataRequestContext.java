package agroludos.presentation.reqresh;

import java.util.Map;

import adisys.server.presentation.req.DataFieldException;

public class DataRequestContext extends AdiRequestContext{

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
}
