package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;

public class EmptyRequestContextImpl implements EmptyRequestContext{
	private AgroRequest request;
	
	@Override
	public void initialize(AgroRequest request) {
		this.request = request;
	}

	@Override
	public String getCommandName() {
		return request.getCommandName();
	}

	@Override
	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public boolean isParam() {
		return request.isParameter();
	}

	@Override
	public AgroRequest getRequest() {
		return this.request;
	}
	
}
