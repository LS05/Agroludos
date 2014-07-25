package agroludos.req.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import agroludos.presentation.reqresh.AgroRequestContext;

abstract public class CommandMapper {
	
	CommandMap cmdMap;
	
	Class classToCall;
	
	public abstract Class getResourceClass();
	
	public CommandMapper(){
		classToCall = this.getResourceClass();
		this.cmdMap = new CommandMap(this.classToCall);
	}
	
	public Object execute(AgroRequestContext request) {
		boolean param = request.isParam();
		Object obj = null;
		Method m = cmdMap.getMethod(request.getCommand());
		try {
			if(param)
				obj = m.invoke(classToCall, request);
			else
				obj = m.invoke(classToCall);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
		}
		return obj;
	}
}
