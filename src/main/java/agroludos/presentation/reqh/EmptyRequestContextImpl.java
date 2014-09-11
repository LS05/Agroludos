package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;
import agroludos.to.AgroludosTO;

public class EmptyRequestContextImpl implements AgroRequestContext{
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
		return request.isParam();
	}

	@Override
	public AgroRequest getRequest() {
		return this.request;
	}

	@Override
	public AgroludosTO getData() {
		return null;
	}
	
}
