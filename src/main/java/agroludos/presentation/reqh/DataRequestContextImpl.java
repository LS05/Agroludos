package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataRequest;
import agroludos.to.AgroludosTO;

public class DataRequestContextImpl implements AgroRequestContext{
	
	protected DataRequest richiesta;
	protected boolean param;
	
	public void initialize(AgroRequest request) {
		this.richiesta = (DataRequest)request;
		this.param = request.isParam();
	}
	
	public boolean isParam(){
		return this.param;
	}
	
	@Override
	public String getCommandName() {
		return this.richiesta.getCommandName();
	}

	@Override
	public AgroludosTO getData(){
			return this.richiesta.getData();
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
