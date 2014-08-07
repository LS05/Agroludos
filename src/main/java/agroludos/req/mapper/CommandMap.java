package agroludos.req.mapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CommandMap {
	Map methods;
	
	CommandMap(Class c){
		methods = new HashMap();
		Method[] classMethods = c.getMethods();
		for(int i = 0; i < classMethods.length; i++)
			methods.put(classMethods[i].getName(), classMethods[i]);
	}
	
	Method getMethod(String methodName){
		return (Method)methods.get(methodName);
	}

}
