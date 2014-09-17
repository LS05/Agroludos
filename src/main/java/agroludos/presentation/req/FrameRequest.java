package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

class FrameRequest implements DataRequest{
	private AgroludosTO reqData;
	private String commandName;
	private AgroSession session;
	
	
	FrameRequest(AgroludosTO data, String commandName, AgroSession session) {
		this.commandName = commandName;
		this.reqData = data;
		this.session = session;
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

	@Override
	public AgroSession getSession() {
		return this.session;
	}
}
