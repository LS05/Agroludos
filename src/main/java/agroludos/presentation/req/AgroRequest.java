package agroludos.presentation.req;

public abstract class AgroRequest {
	String commandName;
	boolean flagParam;
	
	AgroRequest(String commandName){
		this.commandName = commandName;
	}
	
	public String getCommand(){
		return this.commandName;
	}
	
	public abstract boolean isParameter();
	
	public abstract Object getData(Object key) throws DataFieldException;
	
	public abstract Object getData();
}