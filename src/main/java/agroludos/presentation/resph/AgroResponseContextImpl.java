package agroludos.presentation.resph;

import agroludos.presentation.resp.AgroResponse;

class AgroResponseContextImpl implements AgroResponseContext{
	private AgroResponse response;
	private String viewName;
	private Object mainData;
	
	@Override
	public void setLogicalViewName(String viewName){
		this.viewName = viewName;
	}
	
	@Override
	public String getLogicalViewName(){
		return this.viewName;
	}

	@Override
	public void setResponse(AgroResponse response) {
		this.response = response;
		this.response.setResponse(this.mainData);
	}

	@Override
	public AgroResponse getResponse() {
		return this.response;
	}

	@Override
	public void setData(Object data) {
		this.mainData = data;
	}

	@Override
	public Object getData() {
		return this.mainData;
	}
}