package agroludos.presentation.controller.mapper;

class CommandImpl implements Command{
	private String succPath;
	private String failPath;
	private String className;
	private String viewName;
	
	void setSuccessPath(String path){
		this.succPath = path;
	}
	
	void setFailPath(String path){
		this.failPath = path;
	}

	public String getSuccPath() {
		return succPath;
	}

	public String getFailPath() {
		return failPath;
	}

	public void setSuccPath(String succPath) {
		this.succPath = succPath;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public String getViewName() {
		return this.viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}	
}