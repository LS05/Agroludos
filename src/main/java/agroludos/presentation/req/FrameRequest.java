package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

class FrameRequest extends DataRequest {
	private AgroludosTO reqData;
	
	FrameRequest(AgroludosTO data, String commandName) {
		super(commandName, true);
		this.reqData = data;
	}

	@Override
	public AgroludosTO getData() {
		return this.reqData;
	}
}
