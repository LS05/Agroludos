package agroludos.presentation.req;

public abstract class DataRequest extends AgroRequest{
	
	DataRequest(String commandName, boolean isParam){
		super(commandName, isParam);
	}
	
	public abstract Object getData(Object key) throws DataFieldException;
	
	public abstract Object getData();
	
}
