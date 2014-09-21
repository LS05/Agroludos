package agroludos.presentation.req;

//getFromName
public interface AgroRequest {
	public String getCommandName();
	
	public boolean isParam();
	
	public AgroSession getSession();
	
	public String getFromName();
}