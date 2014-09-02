package agroludos.presentation.controller.mapper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class CommandMap {
	private Map<String, Method> methods;
	
	CommandMap(Class<?> c){
		this.methods = new HashMap<String, Method>();
		Method[] classMethods = c.getMethods();
		
		for(int i = 0; i < classMethods.length; i++)
			this.methods.put(classMethods[i].getName(), classMethods[i]);
	}
	
	CommandMap(String className) throws ClassNotFoundException {
		Class<?> c = Class.forName(className);
		this.methods = new HashMap<String, Method>();
		Method[] classMethods = c.getMethods();
		
		for(int i = 0; i < classMethods.length; i++)
			this.methods.put(classMethods[i].getName(), classMethods[i]);
	}
	
	Method getMethod(String methodName){
		return this.methods.get(methodName);
	}

}