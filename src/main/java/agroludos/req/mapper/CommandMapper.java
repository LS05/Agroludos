package agroludos.req.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommandMapper implements CommandMapperI{

	private CommandMap cmdMap;
	
	private Object obj;
	
	private String method;
	
	private Object args;
	
	@Override
	public Object execute() throws Throwable {
		this.cmdMap = new CommandMap(this.obj.getClass());
		Object res = null;
		Method m = this.cmdMap.getMethod(method);
		try {
			if(args != null)
				res = m.invoke(this.obj, args);
			else
				res = m.invoke(this.obj);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e.getCause();
		}
		return res;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}

	public void setArgs(Object args) {
		this.args = args;
	}
}
