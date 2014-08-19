package agroludos.req.mapper;

public interface CommandMapperI {
	public void setObj(Object obj);
	public void setMethod(String method);
	public void setArgs(Object args);
	public Object execute() throws Throwable;
}
