package agroludos.presentation.controller.mapper;

import java.lang.reflect.Method;

import agroludos.exceptions.system.MethodNotFoundException;

class CommandMapImpl implements CommandMap {

	@Override
	public Method getMethod(Class<?> c, String methodName) throws MethodNotFoundException{
		Method m = null;
		Method[] classMethods = c.getMethods();

		for(int i = 0; i < classMethods.length; i++){
			String name = classMethods[i].getName();
			if(name.equals(methodName)){
				m = classMethods[i];
				break;
			}
		}

		if(m == null){
			throw new MethodNotFoundException();
		}

		return m;
	}

}