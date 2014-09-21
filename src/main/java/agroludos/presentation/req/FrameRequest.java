package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

class FrameRequest implements DataRequest{
	private AgroludosTO data;
	private String commandName;
	private String fromName;
	private AgroSession session;

	FrameRequest(AgroludosTO data, String commandName, String fromName, AgroSession session) {
		this.data = data;
		this.commandName = commandName;
		this.fromName = fromName;
		this.session = session;
	}

	@Override
	public AgroludosTO getData() {
		return this.data;
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

	@Override
	public String getFromName() {
		return this.fromName;
	}
}