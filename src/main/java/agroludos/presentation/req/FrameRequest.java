package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

class FrameRequest implements DataRequest{
	private AgroludosTO reqData;
	private String commandName;
	
	FrameRequest(AgroludosTO data, String commandName) {
		this.commandName = commandName;
		this.reqData = data;
	}

	@Override
	public AgroludosTO getData() {
		return this.reqData;
	}

	@Override
	public String getCommandName() {
		return this.commandName;
	}

	@Override
	public boolean isParam() {
		return true;
	}
}
