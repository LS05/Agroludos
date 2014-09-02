package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataFieldException;
import agroludos.presentation.req.DataRequest;

public class DataRequestContextImpl implements DataRequestContext{
	
	protected DataRequest richiesta;
	protected boolean param;
	
	public void initialize(AgroRequest request) {
		this.richiesta = (DataRequest)request;
		this.param = request.isParameter();
	}
	
	public boolean isParam(){
		return this.param;
	}
	
	@Override
	public String getCommandName() {
		return this.richiesta.getCommandName();
	}

	@Override
	public Object getData(Object key) throws DataFieldException{
			return this.richiesta.getData(key);
	}

	public String getStringData(Object key) throws DataFieldException{
		return this.richiesta.getData(key).toString();
	}

	@Override
	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public AgroRequest getRequest() {
		return this.richiesta;
	}
}
