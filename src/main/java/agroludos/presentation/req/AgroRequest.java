package agroludos.presentation.req;

public abstract class AgroRequest {
	String commandName;
	boolean flagParam;
	
	public void setCommand(String command){
		this.commandName = command;
	}
	
	public String getCommand(){
		return this.commandName;
	}
	
	public abstract boolean isParameter();
	
	public abstract Object getData(Object key) throws DataFieldException;
	public abstract Object getData();
}