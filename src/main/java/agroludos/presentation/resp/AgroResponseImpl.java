package agroludos.presentation.resp;

class AgroResponseImpl implements AgroResponse {
	private Object mainData;
	
	@Override
	public void setResponse(Object resp){
		this.mainData = resp;
	}
	
	@Override
	public Object getRespData(){
		return this.mainData;
	}
}