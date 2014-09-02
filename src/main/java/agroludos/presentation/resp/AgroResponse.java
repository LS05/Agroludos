package agroludos.presentation.resp;

public interface AgroResponse {

	void setResponse(Object resp);

	void setViewName(String viewName);

	void setViewPath(String viewPath);

	Object getRespData();

	String getViewPath();

	String getViewName();

}