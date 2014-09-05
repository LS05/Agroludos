package agroludos.presentation.controller.mapper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import agroludos.exceptions.MethodNotFoundException;

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
	
	Method getMethod(String methodName) throws MethodNotFoundException{
		Method m = null;
		
		if(this.methods.containsKey(methodName)){
			m = this.methods.get(methodName);
		} else {
			throw new MethodNotFoundException("Metodo: " + methodName + ". Metodo non presente.");
		}
		
		return m;
	}

}