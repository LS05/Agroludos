package agroludos.presentation.req;

public abstract class AgroRequest {
	protected String commandName;
	protected boolean isParam;
	
	AgroRequest(String commandName, boolean isParam){
		this.commandName = commandName;
		this.isParam = isParam;
	}
	
	public String getCommandName(){
		return this.commandName;
	}
	
	public boolean isParam(){
		return this.isParam;
	}
}