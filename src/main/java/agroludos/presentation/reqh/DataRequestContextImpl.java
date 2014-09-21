package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataRequest;
import agroludos.to.AgroludosTO;

class DataRequestContextImpl implements AgroRequestContext{
	
	protected DataRequest request;
	protected boolean param;
	
	public void initialize(AgroRequest request) {
		this.request = (DataRequest)request;
		this.param = request.isParam();
	}
	
	public boolean isParam(){
		return this.param;
	}
	
	@Override
	public String getCommandName() {
		return this.request.getCommandName();
	}

	@Override
	public AgroludosTO getData(){
			return this.request.getData();
	}

	@Override
	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public AgroRequest getRequest() {
		return this.request;
	}

	@Override
	public String getFromName() {
		return this.request.getFromName();
	}
}
