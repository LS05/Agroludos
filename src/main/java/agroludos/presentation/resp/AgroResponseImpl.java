package agroludos.presentation.resp;

class AgroResponseImpl implements AgroResponse {
	private Object mainData;
	private String viewPath;
	private String viewName;
	
	@Override
	public void setResponse(Object resp){
		this.mainData = resp;
	}
	
	@Override
	public void setViewName(String viewName){
		this.viewName = viewName;
	}
	
	@Override
	public void setViewPath(String viewPath){
		this.viewPath = viewPath;
	}
	
	@Override
	public Object getRespData(){
		return this.mainData;
	}

	@Override
	public String getViewPath() {
		return viewPath;
	}

	@Override
	public String getViewName() {
		return viewName;
	}
}