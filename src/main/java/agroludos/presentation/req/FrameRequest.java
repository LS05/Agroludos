package agroludos.presentation.req;

import java.util.Map;

class FrameRequest extends DataRequest {
	private Map<String, String> reqData;
	
	FrameRequest(Map<String, String> data, String commandName) {
		super(commandName, true);
		this.reqData = data;
	}
	
	FrameRequest(String commandName) {
		super(commandName, false);
	}

	@Override
	public Object getData(Object key) throws DataFieldException{
		Object data = null;
		
		if(this.reqData.containsKey(key))
			data = this.reqData.get(key);
		else
			throw new DataFieldException("Data Field: " + key.toString() + " Inesistente!");
		
		return data;
	}

	@Override
	public Object getData() {
		return this.reqData;
	}
}
