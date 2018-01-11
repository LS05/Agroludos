package agroludos.presentation.controller.mapper;

class CommandImpl implements Command{
	private String succView;
	private String failView;
	private String className;

	public String getSuccView() {
		return succView;
	}

	public void setSuccView(String succView) {
		this.succView = succView;
	}
	
	public String getFailView() {
		return this.failView;
	}
	
	public void setFailView(String failView) {
		this.failView = failView;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}
}