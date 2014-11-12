package agroludos.presentation.controller.mapper;

import java.lang.reflect.Method;

import agroludos.exceptions.MethodNotFoundException;

public interface CommandMap {

	Method getMethod(Class<?> c, String methodName) throws MethodNotFoundException;

}