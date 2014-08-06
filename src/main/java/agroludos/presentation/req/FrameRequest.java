package agroludos.presentation.req;

import java.util.ArrayList;
import java.util.Map;

/**
 * Modificare il Nome di questa classe
 * @author lucasuriano
 *
 */

public class FrameRequest extends AgroRequest{
	private Map<String, String> reqData;
	
	public FrameRequest(){ }
	
	public FrameRequest(Map<String, String> data, String commandName) {
//		super(commandName);
		this.reqData = data;
		this.flagParam = true;
	}
	
	public FrameRequest(String commandName) {
//		super(commandName);
		this.flagParam = false;
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
	
	public ArrayList getAllData(){
		ArrayList<String> res = new ArrayList<String>();
		for(String str : this.reqData.values())
			res.add(str);
		return res;
	}

	@Override
	public boolean isParameter() {
		return this.flagParam;
	}

	@Override
	public Object getData() {
		return this.reqData;
	}
}
