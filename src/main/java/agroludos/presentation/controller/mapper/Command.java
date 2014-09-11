package agroludos.presentation.controller.mapper;

public interface Command {

	String getSuccPath();

	String getFailPath();
	
	String getViewName();
	
	String getClassName();
}